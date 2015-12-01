
import vlc

vlc_instance = vlc.Instance()

class Sound(object):
    """docstring for Sound"""
    def __init__(self, path):
        super(Sound, self).__init__()
        self.media = vlc_instance.media_new(path)
        self.player = vlc_instance.media_player_new()

    def play(self, volume=80, milli_offset=0):
        if(volume > 100):
            raise Exception("Volume above 100")

        if(volume < 0):
            raise Exception("Volume below 100")

        self.player.audio_set_volume(volume)
        self.player.set_media(self.media)
        self.player.play()
        self.player.set_time(milli_offset)

    def change_volume(self, volume):
        self.player.audio_set_volume(volume)

    def stop(self):
        self.player.stop()