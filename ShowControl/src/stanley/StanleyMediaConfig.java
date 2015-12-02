package stanley;

import media.screens.FileVideo;

public class StanleyMediaConfig {
	public FileVideo pictureOfStanley = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/me/me.jpg");
	
	public FileVideo bigBuilding = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/big_building.jpg");
	
	public FileVideo fourTwoSevenLeft = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/427/left.png");
	public FileVideo fourTwoSevenMiddle = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/427/middle.png");
	public FileVideo fourTwoSevenRight = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/427/right.png");
	
	public FileVideo bigFour = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/427/4.png");
	public FileVideo bigTwo = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/427/2.png");
	public FileVideo bigSeven = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/427/7.png");
	
	public FileVideo ordersOne = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/orders/orders1.png");
	public FileVideo ordersTwo = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/orders/orders2.png");
	public FileVideo ordersThree = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/orders/orders3.png");
	
	public FileVideo day = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/monthdayyear/day.png");
	public FileVideo month = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/monthdayyear/month.png");
	public FileVideo year = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/monthdayyear/year.png");
	
	public FileVideo happy = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/happy.png");
	
	public FileVideo officeLeft = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/office/left.png");
	public FileVideo officeMiddle = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/office/middle.png");
	public FileVideo officeRight = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/office/right.png");
	
	public FileVideo doorsLeft = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/doors/door_left.jpg");
	public FileVideo doorsRight = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/doors/door_right.jpg");
	
	public FileVideo meetingRoom= new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/meeting room/meeting_room.jpg");
	
	public FileVideo firePlace = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/boses/FirePlace.flv");
	public FileVideo woodenWall = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/boses/middle.jpg");
	public FileVideo bossesOffice = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/boses/right.jpg");
	
	public FileVideo blackMonitors = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/mind_controls/blacks.png");
	
	public FileVideo monitors1 = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/mind_controls/left.png");
	public FileVideo monitors2 = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/mind_controls/other.png");
	public FileVideo monitors3 = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/mind_controls/right.png");
	public FileVideo mindControls = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/mind_controls/controls.png");
	
	public FileVideo loungeLeft = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/lounge/left.png");
	public FileVideo loungeMiddle = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/lounge/middle.png");
	public FileVideo loungeRight = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/lounge/right.png");
	
	public FileVideo landscapes = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/landscapes.mp4");
	public FileVideo death = new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/death/34seconds.mp4");
	
	public FileVideo[] slides = new FileVideo[7];
	
	public StanleyMediaConfig()
	{
		for(int i = 0; i < 7; i++)
			slides[i]= new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/slides/presentation/Slide"+(i+1)+".jpg");
	}
}
