from array import array
from ola.ClientWrapper import ClientWrapper
from ola.DMXConstants import DMX_MIN_SLOT_VALUE, DMX_MAX_SLOT_VALUE, \
    DMX_UNIVERSE_SIZE

import struct
import socket

UNIVERSE = 1

client_wrapper = ClientWrapper()
client = client_wrapper.Client()

dmx_data = array('B', [DMX_MIN_SLOT_VALUE] * DMX_UNIVERSE_SIZE)
expected_line_length = 4 * DMX_UNIVERSE_SIZE + 1 # 4 characters for each slot, plus the newline

def process_line(line):
    global dmx_data

    length = len(line)

    if(length == 0):
        print("Recieved line of 0, aborted")
        return False

    if(length != expected_line_length):
        print "Expected {} to be of length {} but was {}".format(line, expected_line_length, len(line))
        return True

    print(line)

    position = 0;
    for x in range(DMX_UNIVERSE_SIZE):
        myInt = int(line[position:position+4])
        if(myInt > DMX_MAX_SLOT_VALUE):
            print "Overflow!!"
            myInt = DMX_MAX_SLOT_VALUE
        dmx_data[x] = myInt
        position += 4

    client.SendDmx(UNIVERSE, dmx_data)
    return True


def start_server():

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(('', 5000))

    print "Listening to for client"
    s.listen(1)
    conn, addr = s.accept()
    reader = conn.makefile('r')

    print "Client recieved, starting loop"

    try:
        while process_line(reader.readline()):
            pass
    finally:
        print "Exiting server"
        s.close()
        reader.close()
   
print("Bringing up house")
empty = array('B', [DMX_MIN_SLOT_VALUE] * DMX_UNIVERSE_SIZE)
empty[106] = DMX_MAX_SLOT_VALUE
empty[107] = DMX_MAX_SLOT_VALUE
client.SendDmx(UNIVERSE, empty)

print("Can you see the house?")
if not raw_input("y/n").startswith('y'):
    print("Aborting")
    exit(0)

try:
    start_server()
finally:
    empty = array('B', [DMX_MIN_SLOT_VALUE] * DMX_UNIVERSE_SIZE)
    client.SendDmx(UNIVERSE, empty)
    client_wrapper.Stop()

