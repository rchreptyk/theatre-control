from array import array
from ola.ClientWrapper import ClientWrapper
from ola.DMXConstants import DMX_MIN_SLOT_VALUE, DMX_MAX_SLOT_VALUE, \
    DMX_UNIVERSE_SIZE

UPDATE_INTERVAL = 500  # In ms, this comes about to ~40 frames a second
SHUTDOWN_INTERVAL = 10000  # in ms, This is 10 seconds
DMX_DATA_SIZE = 100
UNIVERSE = 1

first_channels = [ 1 ]
second_channels = [ 2 ]

is_first = True

client_wrapper = ClientWrapper()
client = client_wrapper.Client()

def flip():
    global is_first

    if is_first:
        print("Turning on first set of lights")
        turn_on = first_channels
    else:
        print("Turning on second set of lights")  
        turn_on = second_channels

    dmx_data = array('B', [DMX_MIN_SLOT_VALUE] * DMX_UNIVERSE_SIZE)

    for x in turn_on:
        dmx_data[x - 1] = 127

    is_first = not is_first

    client.SendDmx(UNIVERSE, dmx_data)
    client_wrapper.AddEvent(UPDATE_INTERVAL, flip)


def stop():
    print("Stopping")
    client_wrapper.Stop()


print("Registering events")
client_wrapper.AddEvent(SHUTDOWN_INTERVAL, stop)
client_wrapper.AddEvent(UPDATE_INTERVAL, flip)

print("Running Wrapper")
client_wrapper.Run()
