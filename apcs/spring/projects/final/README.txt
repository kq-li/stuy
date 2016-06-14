                                                        
,-.----.                                                
\    /  \                ___      ,---,                 
|   :    \             ,--.'|_  ,--.' |                 
|   |  .\ :            |  | :,' |  |  :                 
.   :  |: |            :  : ' : :  :  :      .--.--.    
|   |   \ : ,--.--.  .;__,'  /  :  |  |,--. /  /    '   
|   : .   //       \ |  |   |   |  :  '   ||  :  /`./   
;   | |`-'.--.  .-. |:__,'| :   |  |   /' :|  :  ;_     
|   | ;    \__\/: . .  '  : |__ '  :  | | | \  \    `.  
:   ' |    ," .--.; |  |  | '.'||  |  ' | :  `----.   \ 
:   : :   /  /  ,.  |  ;  :    ;|  :  :_:,' /  /`--'  / 
|   | :  ;  :   .'   \ |  ,   / |  | ,'    '--'.     /  
`---'.|  |  ,     .-./  ---`-'  `--''        `--'---'   
  `---`   `--`---'                                      


========
Gameplay
========

Before playing, you have to compile (javac *.java) and then run (java Game).

The game involves two players on a hex grid - Player 1 starts at the top vertex,
Player 2 starts at the bottom vertex. The current turn player is marked in green.
Each player chooses an action to take, then both actions occur simultaneously
after both have confirmed. Thus, each player's intent is invisible to the other
(theoretically).

On each turn, players may either move or attack. Movement is signified by cyan
hexes that mark the potential destinations, and can be initiated by left clicking
the current player or clicking the blue arrow. By default, players can move one
hex per turn.

Attacking is signified by red hexes that mark the hexes in a projectile's path.
Upon right clicking the current player or clicking the sword, the player will be
able to choose the projectile's exact path for the next several turns.
Projectiles move at the rate of one hex per turn, have 5 range, and do 5 damage
by default.

Note that only one of movement and attacking may take place per turn. In order to
see the input path for movement or attack, hover over the player. In order to see
the path of a projectile in transit, hover over it. In order to cancel a pending
command, click on either the move or attack button or middle click.

Since the projectile paths are unknown to the opponent (provided he/she is not
watching you play), the opponent must predict the path of your projectile while
trying to hit you with his/her own projectiles. The game ends when either
player's health drops below 0, from a default maximum of 10.


==========================
Design and Class Hierarchy
==========================

Game.java encapsulates the main game engine, window, and logic, and contains
references to most of the other objects necessary to run the game.

Grid.java encapsulates the hex grid and methods pertaining to it, eliminating
surface-level computations that would otherwise be commonplace.

Renderer.java encapsulates the rendering methods that utilize the Graphics2D
class, and are called by Game.java in paintComponent().

GUI.java encapsulates the graphical user interface and all its methods,

Entity.java is an abstract class for all entities - that is, non-interface
elements of the game - and provides a foundation for these entities. It is
implemented by Player.java, Projectile.java, and Tracer.java. Each entity
contains its own shape to allow the Renderer ro easily display it.

Tracer.java describes tracer objects, which are objects that contain a
sequence of hexes and are used to store and display action intents.

Button.java is a rudimentary button class.

Hex.java is a class detailing a hexagon and all the extra data each hex
stores in this game, like what entities might be on it at the moment.

HexComparator.java is the comparator used to order hexes in the Grid
gridToPQ() method.

Throughout this project, I kept going back to optimize certain sections of code,
preferring to use additional storage rather than conserve runtime generally. I
also tried my best to stick to object-oriented programming paradigms and made
(most) everything modular and unit-testable.


==============================
Algorithms and Data Structures
==============================

In order to optimize the project, data structures with random accessors/mutators
of O(n) or better runtime were important. This project therefore includes many
ArrayLists, a LinkedList Queue implementation, and of course a Priority Queue.
I used various mathematics algorthms and structures to process hexes
efficiently. 


==========
Conclusion
==========

In the end, this project turned out decently well. I hadn't planned on making a
GUI, but I realized that the game was way too unintuitive, with or without
explanation. 

Big O for the methods in this project are mostly O(n) - even accessing all hexes,
which should take O(n^3) because of (x, y, z), becomes O(n) via the gridToAL()
function and the Grid instance variable _list.

In the future, I would expand this project by either making an AI for the game
or making a server to allow individual computers running a client to connect.
Both of these would eliminate the "cheating" problems that come with pass and
play, but would also require a substantial amount of effort. The code isn't
exactly pretty - there are some inconsistencies - but it is largely modular and
adherent to the object-oriented paradigms of abstraction, encapsulation, and
inheritance, and to some degree polymorphism.
