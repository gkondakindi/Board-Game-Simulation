 ************************************************************************************
 * Files Included   :   OutspokenConstants.java, Outspoken.java                     *
 * Description      :   Board Game simulation for 10X10 Grid                        *
 * Author           :   Gouthami Kondakindi                                         *
 * Date             :   4/12/2014                                                   *
 * Compilation      :   javac Outspoken.java OutspokenConstants.java                *                
 * Run              :   java Outspoken                                              *
 ************************************************************************************
I have used Java for the coding assignment.
I have made the following assumptions:
    1. Grid starts with (0,0) and ends with (9,9)
    2. The first command that user should enter to begin playing in PLACE <x> <y> <d>
    3. User can continue playing as long as he wants. But, when user enters STATUS, an option is given for user to continue or quit.
       This is the only way for the user to quit the game.
    4. If initial position is (0,0,NORTH) and user enters FORWARD, the new position will be (0,1,NORTH) unlike the description 
       which says that the position should be (1,0,NORTH). I made this assumption because moving forward while facing NORTH 
       should make Avator move up in the grid which means Y coordinate has to be incremented and not X coordinate.
       Eg: PLACE 0 0 NORTH
           FORWARD
           FORWARD
           TURN RIGHT
           FORWARD
           STATUS
           According to description, output should be "Avator is at (2, 1) facing EAST". But my program returns "Avator is at (1, 2) facing EAST"
    5. Once the user starts playing, game does not exit when user enters a wrong command but just shows error message to user and asks him to retype.
       This is because user might have typed a wrong command by mistake. Quitting the game just because of this might be irritating to the user.
    6. When user gives a wrong command at the start of the game, the game is stopped and user is shown an error message unlike assumption 4.
    7. When user types a PLACE command which makes Avator go out of the grid, the command is ignored and a message "Position should not cross the 10X10 grid" is displayed to user.
    8. When user gives any other commands which make Avator go out of the grid, the command is just ignored and no message is displayed to user.
    9. User commands can be in either UpperCase or LowerCase or a combination of both.
I tested the code for several cases and it gave expected results.
I ensured maintainability of code in terms of grid size by enabling developer to just modify 2 variables to specify grid size in X and Y directions 
without having to modify entire code. Grid can even be a rectangle instead of a square.
I included several comments in the code to explain what each part of the code does.  

All files should be placed in same directory before compiling. Navigate to that directory to compile.

