/************************************************************************************
 * File             :   Outspoken.java
 * Description      :   Board Game Simulation Main Program
 * Author           :   Gouthami Kondakindi
 * Date             :   4/12/2014
 ************************************************************************************/
import java.io.*;

public class Outspoken 
{
    public enum Directions {NORTH, SOUTH, EAST, WEST};
    public static void main(String[] args) throws IOException 
    {
        int positionX, positionY, stop = 0;
        Directions facing;
        String input, command;
        String inputParameters[], commandParameters[];
        Outspoken obj = new Outspoken();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            obj.gameRules();
            System.out.println("Enter initial position for Avator in PLACE <x> <y> <d> format");
            /*input holds initial position*/
            input = br.readLine(); 
            /*inputParameters[] holds input commands obtained after splitting using whitespace*/
            inputParameters = input.split("\\s"); 
            /*validatePlace() checks if the PLACE command entered by user is in required format
             *This if loop returns if PLACE command is not given properly
             */
            if(!obj.validatePlace(inputParameters)) 
            {
                System.out.println("Game stopped!");
                return;
            }
            /*positionX holds <x> parameter from PLACE <x> <y> <d> command. If user enteres non-numeric data, catch block will handle it*/
            positionX = Integer.parseInt(inputParameters[1]);
            /*positionY holds <y> parameter from PLACE <x> <y> <d> command. If user enteres non-numeric data, catch block will handle it*/
            positionY = Integer.parseInt(inputParameters[2]); 
            /*facing holds <d> parameter from PLACE <x> <y> <d> command.*/
            facing = Directions.valueOf(inputParameters[3].toUpperCase()); 
            /*while loop terminates only when user types "Y". stop variable keeps track of this*/
            while(stop==0) 
            {
                System.out.println("Next Command:");
                /*command holds commands entered by user while playing*/
                command = br.readLine(); 
                /*commandParameters[] holds commands obtained after splitting using whitespace*/
                commandParameters = command.split("\\s");
                /*This if condition validates and performs functions related to commands of length 1 i.e., FORWARD, STATUS*/
                if(commandParameters.length == 1)
                {
                    /*Checks if command entered by user is FORWARD*/
                    if(OutspokenConstants.FORWARD.equals(commandParameters[0].toUpperCase()))
                    {
                        /*If command is FORWARD and current direction is NORTH, Avator moves forward. So, positionY should be incemented.*/
                        if(OutspokenConstants.NORTH.equalsIgnoreCase(facing.name()))
                        {
                            /*Increments positionY only when Avator does not cross grid*/
                            if(positionY<OutspokenConstants.gridSizeY-1)
                                positionY++;
                        }
                        /*If command is FORWARD and current direction is SOUTH, Avator moves backward. So, positionY should be decremented.*/
                        if(OutspokenConstants.SOUTH.equalsIgnoreCase(facing.name()))
                        {
                            /*Decrements positionY only when Avator does not cross grid*/
                            if(positionY>0)
                                positionY--;
                        }
                        /*If command is FORWARD and current direction is EAST, Avator moves right. So, positionX should be incemented.*/
                        if(OutspokenConstants.EAST.equalsIgnoreCase(facing.name()))
                        {
                            /*Increments positionX only when Avator does not cross grid*/
                            if(positionX<OutspokenConstants.gridSizeX-1)
                                positionX++;
                        }
                        /*If command is FORWARD and current direction is WEST, Avator moves left. So, positionX should be decremented.*/
                        if(OutspokenConstants.WEST.equalsIgnoreCase(facing.name()))
                        {
                            /*Decrements positionX only when Avator does not cross grid*/
                            if(positionX>0)
                                positionX--;
                        }
                    }
                    /*Checks if command entered by user is STATUS*/ 
                    else if(OutspokenConstants.STATUS.equals(commandParameters[0].toUpperCase()))
                    {
                        System.out.println("Avator is at ("+positionX+", "+positionY+") facing "+facing.name());
                        /*Asks user if he wants to continue or quit*/
                        System.out.println("\nDo you want to continue? Y/N");
                        /*Sets stop=1 to come out of while loop only if user wants to quit*/
                        if((br.readLine().toUpperCase()).equals("N"))
                            stop = 1;
                        continue;        
                    }
                    /*else part is executed when command entered by user is not FORWARD or STATUS*/
                    else 
                    {
                        System.out.println("Format error: Please enter valid command");
                        continue;
                    }
                }
                /*This if condition validates and performs functions related to commands of length 2 i.e., TURN LEFT, TURN RIGHT*/
                else if(commandParameters.length == 2)
                {
                    /*If first parameter in command is not TURN, message is displayed to user and continues with next iteration
                     * In next iteration, user is prompted to enter a new command
                     */
                    if(!"TURN".equals(commandParameters[0].toUpperCase()))
                    {
                        System.out.println("Format error: Please enter valid command");
                        continue;
                    }
                    /*If second parameter is not LEFT ot RIGHT, message is displayed to user and continues with next iteration*/
                    if((!OutspokenConstants.RIGHT.equals(commandParameters[1].toUpperCase())) && (!OutspokenConstants.LEFT.equals(commandParameters[1].toUpperCase())))
                    {
                        System.out.println("Format error: Avator can only turn LEFT or RIGHT");
                        continue;
                    }
                    /*If first parameter is TURN and second parameter is RIGHT, variable facing is changed depending on current direction*/ 
                    if(OutspokenConstants.RIGHT.equals(commandParameters[1].toUpperCase()))
                    {
                        /*If current direction=NORTH, turning right makes it EAST*/
                        if(OutspokenConstants.NORTH.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.EAST;
                            continue;
                        }
                        /*If current direction=SOUTH, turning right makes it WEST*/
                        if(OutspokenConstants.SOUTH.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.WEST;
                            continue;
                        }
                        /*If current direction=EAST, turning right makes it SOUTH*/
                        if(OutspokenConstants.EAST.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.SOUTH;
                            continue;
                        }
                        /*If current direction=WEST, turning right makes it NORTH*/
                        if(OutspokenConstants.WEST.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.NORTH;
                            continue;
                        }
                    }
                    /*If first parameter is TURN and second parameter is LEFT, variable facing is changed depending on current direction*/
                    else
                    {
                        /*If current direction=NORTH, turning left makes it WEST*/
                        if(OutspokenConstants.NORTH.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.WEST;
                            continue;
                        }
                        /*If current direction=SOUTH, turning left makes it EAST*/
                        if(OutspokenConstants.SOUTH.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.EAST;
                            continue;
                        }
                        /*If current direction=EAST, turning left makes it NORTH*/
                        if(OutspokenConstants.EAST.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.NORTH;
                            continue;
                        }
                        /*If current direction=WEST, turning left makes it SOUTH*/
                        if(OutspokenConstants.WEST.equalsIgnoreCase(facing.name()))
                        {
                            facing = Directions.SOUTH;
                            continue;
                        }
                    }
                }
                /*This if condition validates and performs functions related to commands of length 4 i.e., PLACE <x> <y> <d>*/
                else if(commandParameters.length == 4)
                {
                    /*If command is not in proper format or command makes Avator cross the grid, command is ignored and control goes to next iteration*/
                    if(!obj.validatePlace(commandParameters))
                        continue;
                    /*If command is OK to be executed, new PositionX, positionY, facing values are stored*/
                    else
                    {
                        positionX = Integer.parseInt(commandParameters[1]);
                        positionY = Integer.parseInt(commandParameters[2]);
                        facing = Directions.valueOf(commandParameters[3].toUpperCase());
                    }
                }
                /*If command is neither of length 1,2 or 4, then it is an invalid command*/
                else
                {
                    System.out.println("Please enter valid command");
                    continue;
                }
            }
            /*When user types STATUS and then wishes to quit by typing "Y", stop is set to 1.
             *During next iteration, while() loop is broken because stop!=0. This statement i.e., Game stopped! will be printed in such case             * 
             */
            System.out.println("Game stopped!");
        }
        /*catch block handles case where user enters non-numeric data in <x> or <y> fields in PLACE <x> <y> <d>*/
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage() + " should be a number");
        }
    }
    
    /*Displays all game rules to the user initially*/
    void gameRules()
    {
        System.out.println("Rules of the Game:");
        System.out.println("PLACE <x> <y> <d> - Place the avator at position x, y on the grid, facing direction d");
        System.out.println("TURN LEFT - Tell the avator to make a left turn");
        System.out.println("TURN RIGHT - Tell the avator to make a right run");
        System.out.println("FORWARD - Tell the avator to take one step forward");
        System.out.println("STATUS - Report the current position and direction of the avator\n");
        System.out.println("You can begin playing!");
    }
    
    /*Checks if PLACE command is in proper format and returns TRUE or FALSE*/
    boolean validatePlace(String place[])
    {
        /*Returns FALSE if length of command < 4*/
        if(place.length != 4)
        {
            System.out.println("Format error [Place]: Please enter input only in PLACE <x> <y> <d> format");
            return false;
        }
        /*Returns FALSE if 1st parameter in command is not PLACE*/
        if(!"PLACE".equals(place[0].toUpperCase()))
        {
            System.out.println("Format error [Place]: First parameter in input should be PLACE");
            return false;
        }
        /*Returns FALSE if 2nd parameter <x> makes Avator cross grid*/
        if(!validatePosition(Integer.parseInt(place[1]),1))
        {
            System.out.println("Position should not cross the 10X10 grid");
            return false;
        }
        /*Returns FALSE if 3rd parameter <y> makes Avator cross grid*/
        if(!validatePosition(Integer.parseInt(place[2]),2))
        {
            System.out.println("Position should not cross the 10X10 grid");
            return false;
        }
        /*Returns FALSE if 4th parameter <d> is not one of NORTH,SOUTH,EAST or WEST*/
        if(!isEnumType(place[3]))
        {
            System.out.println("Format error [Place]: Direction should only be one of EAST, WEST, NORTH, SOUTH");
            return false;
        }
        /*Returns TRUE if command is correct and does not make Avator move out of grid*/
        return true;
    }
    
    /*Takes 2 parameters and returns TRUE or FALSE. pos tells position in grid and coord tells if it is X or Y coordinate. 
     * coord = 1 => X coordinate on grid
     * coord = 2 => Y coordinate on grid
     * This is done for maintainability purpose. 
     * If user wants a 7X3 grid instead on 10X10, checking gridSizeX and gridSizeY seperately like below will help
     */
    boolean validatePosition(int pos,int coord)
    {
        /*Returns TRUE if positionX is not less than 0 or not greater than grid size i.e., if it does not cross grid*/
        if(coord == 1 && pos>=0 && pos<OutspokenConstants.gridSizeX)
        {
            return true;
        }
        /*Returns TRUE if positionY is not less than 0 or not greater than grid size i.e., if it does not cross grid*/
        else if(coord == 2 && pos>=0 && pos<OutspokenConstants.gridSizeY)
        {
            return true;
        }
        /*Returns FALSE for all other cases*/
        else
            return false;
    }

    /*Chcks if direction entered by user is valid i.e., is it is one of the directions given in enum variable Directions*/
    boolean isEnumType(String name)
    {
        /*Returns TRUE if direction is one of the values in the enum variable Direction. If not, catch will handle it*/
       try
       {
          Directions.valueOf(name.toUpperCase());
          return true;
       }
       /*If value entered for direction is not in the enum variable, catch will handle it and return FALSE*/
       catch(Exception e)
       {
          return false;
       }
    }
}
