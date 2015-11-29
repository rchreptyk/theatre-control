from media.screen import Screen

from PyQt4 import QtGui
import sys

class Screens(object):
    """docstring for Screens"""
    def __init__(self):
        super(Screens, self).__init__()
        self.app = QtGui.QApplication(sys.argv)

        self.left = Screen(self.app, 'left', '/Users/russell/Desktop/Workspace/stanely/media/screen_init/left.png')
        self.right = Screen(self.app, 'right', '/Users/russell/Desktop/Workspace/stanely/media/screen_init/right.png')
        self.center = Screen(self.app, 'center', '/Users/russell/Desktop/Workspace/stanely/media/screen_init/center.png')

    def get_screens(self):
        return {
            'left': self.left,
            'right': self.right,
            'center': self.center
        }

    def start(self):
        try:
            self.app.exec_()
        finally:
            self.stop()
       

    def stop(self):
        self.left.close()
        self.right.close()
        self.center.close()
        self.app.quit()
        