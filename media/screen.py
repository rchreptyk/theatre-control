
import vlc

defaultWidth = 960
defaultHeight = 540

from PyQt4 import QtGui
from PyQt4 import QtCore

class Screen(object):
    """Create a screen for the display"""
    def __init__(self, application, screenName, full_screen=False):
        self.screenName = screenName
        self.frame = QtGui.QFrame()
        self.frame.resize(defaultWidth,defaultHeight)
        self.frame.show()

        self.frame.setAutoFillBackground(True)
        palette = self.frame.palette()
        palette.setColor(self.frame.backgroundRole(), QtCore.Qt.black)
        self.frame.setPalette(palette)

        if(full_screen):
            self.frame.showFullScreen()

        self.vlc = vlc.Instance('--no-audio', '--fullscreen')
        self.player = self.vlc.media_player_new()
        
        self.player.set_nsobject(self.frame.winId())
        
    def play_video(self, video):
        media = self.vlc.media_new(video)
        self.player.set_media(media)
        self.player.play()

    def stop():
        self.player.stop()