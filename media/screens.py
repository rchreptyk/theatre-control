from media.screen import Screen

from PyQt4 import QtGui
import sys

class Screens(object):
    """docstring for Screens"""
    def __init__(self):
        super(Screens, self).__init__()
        self.app = QtGui.QApplication(sys.argv)

        self.left = Screen(self.app, 'left')
        self.right = Screen(self.app, 'right')
        self.center = Screen(self.app, 'center')

    def get_screens(self):
        return {
            'left': self.left,
            'right': self.right,
            'center': self.center
        }

    def start(self):
        self.app.exec_()

        