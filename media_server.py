import socket
import os

from threading import Thread
from media.screens import Screens
from media.sound import Sound

pipe_name = 'media_pipe'

REQUEST_TYPE = 0
SCREEN_NAME = 1
MEDIA_PATH = 2

SOUND_PATH = 1
SOUND_VOLUME = 1

class MediaServer(object):
    """docstring for MediaServer"""
    def __init__(self, screens):
        super(MediaServer, self).__init__()
        self.screens = screens

        if os.path.exists(pipe_name):
            os.remove(pipe_name)
        os.mkfifo(pipe_name)
        
    def start(self):
        self.reader_thread = Thread(target=self.reader_loop)
        self.reader_thread.start()

    def reader_loop(self):
        with open(pipe_name) as self.media_pipe:
            while True:
                # try:
                    request = self.media_pipe.readline().strip()
                    if request == '':
                        break
                    print("Recieved request" + request)
                    self.process_request(request)
                # except Exception, e:
                #     print(e)

    def process_request(self, request):
        parts = request.split()
        if len(parts) == 0:
            return

        if parts[REQUEST_TYPE] == 'screen':
            self.process_screen(parts)
        elif parts[REQUEST_TYPE] == 'sound':
            self.process_sound(parts)
        else:
            print("Invalid command " + parts[REQUEST_TYPE])

    def process_screen(self, parts):
        screen_name = parts[SCREEN_NAME]
        print("Looking for screen " + screen_name)
        screen = self.screens[screen_name]
        screen.play_video(parts[MEDIA_PATH])

    def process_sound(self, parts):
        volume = 80
        sound = Sound(parts[SOUND_PATH])

        if(len(parts) == 3):
            volume = int(parts[SOUND_VOLUME])

        sound.play(volume)

screens = Screens()

media_server = MediaServer(screens.get_screens())
media_server.start()

screens.start()