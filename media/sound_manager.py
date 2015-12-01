from media.sound import Sound

class SoundManager(object):
    """docstring for SoundManager"""
    def __init__(self):
        self.sounds = { }

    def play_sound(self, name, path, volume, milli_offset):
        if(name in self.sounds):
            raise Exception("Cannot recreate sound with name " + name)

        sound = Sound(path)
        sound.play(volume, milli_offset)

        self.sounds[name] = sound

    def set_volume(self, name, volume):
        if(not name in self.sounds):
            raise Exception("No sound with name " + name)

        self.sounds[name].change_volume(volume)

    def stop(self, name):
        if(not name in self.sounds):
            raise Exception("No sound with name " + name)

        self.sounds[name].stop()
        del self.sounds[name]
        