
import vlc

vlc_instance = vlc.Instance()

class Sound(object):
    """docstring for Sound"""
    def __init__(self, path):
        super(Sound, self).__init__()
        self.media = vlc_instance.media_new(path)
        self.player = vlc_instance.media_player_new()

    def play(self, volume=80):
        self.player.audio_set_volume(volume)
        self.player.set_media(self.media)
        self.player.play()

    def change_volume(self, volume):
        self.player.audio_set_volume(volume)

    def stop(self):
        self.player.stop()
        self.player.release()