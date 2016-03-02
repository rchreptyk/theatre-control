
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
        self.vlc = vlc.Instance('--no-audio', '--fullscreen', '--sub-source=marq')
        self.player = self.vlc.media_player_new()
        self.player.video_set_marquee_int(vlc.VideoMarqueeOption.marquee_X, 500)
        self.player.video_set_marquee_int(vlc.VideoMarqueeOption.marquee_Y, 200)

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

    def overlay(self, text):
       
        print("Setting string to \"" + text + "\"")

        if(not text):
            print("String is empty")
            self.player.video_set_marquee_string(vlc.VideoMarqueeOption.Text, " ")
            self.player.video_set_marquee_int(vlc.VideoMarqueeOption.Enable, 0)
        else:
            print("Setting text")
            self.player.video_set_marquee_string(vlc.VideoMarqueeOption.Text, text.replace("|", "\n"))
            self.player.video_set_marquee_int(vlc.VideoMarqueeOption.Enable, 1)
        
        # self.player.video_set_marquee_string(vlc.VideoMarqueeOption.Text, text)
        # # refresh gaze location every 10 ms at most
        # self.player.video_set_marquee_int(vlc.VideoMarqueeOption.Timeout,
        #                              int(2.5 * 41))
        
    def play_video(self, video):
        media = self.vlc.media_new(video, "--sub-filter=marq")
        self.player.set_media(media)
        self.player.play()

    def stop(self):
        self.player.stop()

    def close(self):
        print("Closing screen")
        self.player.stop()
        self.frame.close()