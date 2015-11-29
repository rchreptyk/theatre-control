
import vlc

defaultWidth = 960
defaultHeight = 540

from PyQt4 import QtGui
from PyQt4 import QtCore

class ScreenWindow(QtGui.QFrame):

    def __init__(self, player):
        super(QtGui.QFrame, self).__init__()
        self.full_screen = False
        self.player = player

    def mouseDoubleClickEvent(self, event):
        time = self.player.get_time()
        self.player.stop()
        if(not self.full_screen):
            self.showFullScreen()
        else:
            self.showNormal()

        self.full_screen = not self.full_screen
        self.player.set_nsobject(self.winId())
        self.player.play()
        self.player.set_time(time)

class Screen(object):
    """Create a screen for the display"""
    def __init__(self, application, screenName, defaultImage, x = 500, y = 500):
        self.vlc = vlc.Instance('--no-audio', '--fullscreen')
        self.player = self.vlc.media_player_new()

        self.screenName = screenName
        self.frame = ScreenWindow(self.player)
        self.frame.setWindowTitle(screenName)
        self.frame.resize(defaultWidth,defaultHeight)
        self.frame.show()
        self.frame.move(x, y)

        self.frame.setAutoFillBackground(True)
        palette = self.frame.palette()
        palette.setColor(self.frame.backgroundRole(), QtCore.Qt.black)
        self.frame.setPalette(palette)

        self.player.set_nsobject(self.frame.winId())

        media = self.vlc.media_new(defaultImage)
        self.player.set_media(media)
        self.player.play()
        
    def play_video(self, video):
        media = self.vlc.media_new(video)
        self.player.set_media(media)
        self.player.play()

    def stop(self):
        self.player.stop()

    def close(self):
        print("Closing screen")
        self.player.stop()
        self.frame.close()