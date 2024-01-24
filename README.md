# Bubble_Trouble_Game-OOP

Example gameplay:  https://drive.google.com/file/d/11lJTrNrSmBr7rZZef-7idqd2l5yCyvGe/view?usp=sharing   

This program is a clone of the game "Bubble-Trouble" with some parts modified, made using the StdDraw Library. In this program, our player can move right and left on the canvas (X-axis) freely, and to win the game he must hit all the balls above him with an arrow. If the ball is big enough when our player hits the ball, there are created two smaller balls that go in different directions and the player must hit them as well. Also, we lose the game if the balls touch our player or if the playing time shown in the bottom bar runs out. I made the game by separating it into classes to be suitable for object-oriented programming convenience.         
I can summarize what the classes do as follows:      
* **Main class:** I run the game by calling the Environment class in this class.
* **Environment class:** This class is the running class of the game, here I create a loop that will 
enable the game to play, and then I make the game play and finish with the objects I created 
from the Arrow, Ball, Player, and Bar classes and the methods I created in the Environment class.
* **Player class:** Thanks to this class, I create a player object, make its movements, keep its 
coordinates, and set them with methods.
* **Bar class:** Thanks to this class, I create the bar object that appears at the bottom of the game 
and tells the end time of the game, and I keep its duration, color, and size and set them with 
methods.
* **Arrow class:** Thanks to this class, I create the Arrow object that our player will call to explode 
the balls whenever he wants, keep its height, speed, and coordinates and set them with 
methods.
* **Ball class:** Thanks to this class, I create my ball objects that will move in the game, keep their 
movements, speeds, diameters, levels, and coordinates and set them with methods.

Starting of the game and throwing an arrow:        
![image](https://github.com/kerembozkurt2002/Bubble_Trouble_Game-OOP/assets/157289283/f8438fcc-12db-4fe9-85f9-7145c6749662)      
![image](https://github.com/kerembozkurt2002/Bubble_Trouble_Game-OOP/assets/157289283/3a88bdf2-d247-4fcd-9914-08baa9d1d398)        



