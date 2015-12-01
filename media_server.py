import socket
import os
import sys

from threading import Thread
from media.screens import Screens
from media.sound_manager import SoundManager

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
        self.screen_dict = screens.get_screens()
        self.sounds = SoundManager()
        self.stopped = False

        if os.path.exists(pipe_name):
            os.remove(pipe_name)
        os.mkfifo(pipe_name)
        
    def start(self):
        self.reader_thread = Thread(target=self.reader_loop)
        self.reader_thread.start()

    def reader_loop(self):
        with open(pipe_name) as self.media_pipe:
            while not self.stopped:
                try:
                    request = self.media_pipe.readline().strip()
                    if request == '':
                        break
                    print("Recieved request " + request)
                    self.process_request(request)
                except Exception, e:
                    print(e)

        print("Media server stopped")
        self.screens.stop()

    def stop(self):
        self.stopped = True
        if(not hasattr(self, 'media_pipe')):
            with open(pipe_name, 'w'):
                pass
        print("Stopping server")

    def process_request(self, request):
        request_type, space, request  = request.partition(' ')

        if(space == '' or request == ''):
            return

        if request_type == 'screen':
            self.process_screen(request)
        elif request_type == 'sound':
            self.process_sound(request)
        else:
            print("Invalid command " + request_type)

    def process_screen(self, request):
        screen_name, space, request  = request.partition(' ')
        command, space, request  = request.partition(' ')
        print("Looking for screen " + screen_name)
        screen = self.screen_dict[screen_name]

        if(command == 'play'):
            screen.play_video(request)
        elif(command == 'blackout'):
            screen.stop()

    def process_sound(self, request):
        command, space, request  = request.partition(' ')
        sound_name, space, request  = request.partition(' ')

        if(command == 'play'):
            volume_str, space, request  = request.partition(' ')
            milli_offset, space, path  = request.partition(' ')

            print("Playing " + sound_name + " (" + path + ") at volume " + volume_str)

            self.sounds.play_sound(sound_name, path, int(volume_str), int(milli_offset))
        elif(command == 'volume'):
            volume_str, space, request  = request.partition(' ')
            self.sounds.set_volume(sound_name, int(volume_str))
        elif(command == 'stop'):
            self.sounds.stop(sound_name)


screens = Screens()

media_server = MediaServer(screens)
media_server.start()

screens.start()

media_server.stop()
print("Bye")