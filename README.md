# Desktop Based Number Slider Game Application
A Java GUI implementations of revised traditional number slider game application based on Model-View-Control (MVC) pattern.

## Table of Contents:
* [Credits](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-credits)
* [How to Run Project](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-how-to-run-project)
* [Project Information](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-project-information)
* [Design Idea](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-design-idea)
* [Gaming Rule and Game Panel](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-gaming-rule-and-game-panel)
* [Information Panel](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-information-panel)
* [Control Panel and Menu Bar](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-control-panel-and-menu-bar)
* [Exceptions](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-exceptions)
* [References](https://bitbucket.org/chenchenece/number-slider-puzzle-game-app/overview#markdown-header-references)

## Credits

* [Dr. Kristof Cools](http://www.nottingham.ac.uk/engineering/departments/eee/people/kristof.cools) 
* All teaching assistant of Web Based Computing (H63JAV) (Spring 2016) (University of Nottingham)

## How to Run Project

The distributed output app can be refer to './dist/NumberSliderPuzzleGameApp.jar'.

To start, first cd to the './dist' directory and run following command:

<pre>
java -jar "NumberSliderPuzzleGameApp.jar"
</pre>

## Project Information

The mission of this desktop GUI number slider puzzle game application is to generate a shuffled number slider puzzle game and allow users to solve it by sliding numbered block to limited empty space with minimal moves and time. 

This application is highly flexible being able to generate puzzle with customized difficulty level and puzzle size. The general main UI panel can be seen as follows, which consists of four main parts: the menu bar, the game panel, the information panel and setting panel.

![GUI_interface.JPG](https://bitbucket.org/repo/nLdgk7/images/1887326757-GUI_interface.JPG)

As a summary, the features of these four main parts can be listed as follows:

Area                  | Feature 
:-----                | :-----
Menu Bar              | Quick helps, Share, Print puzzle, Exit;
Information Panel     | Puzzle size, Memory status, Current time, Time elapsed, Number of shuffles;  
Setting Panel         | Time elapsed displaying, Reshuffling, Restart, Pause, Resume, Save, Exit;

## Design Idea

In this app, the idea of Model-View-Control (MVC) is fully applied [1, 2], which can be abstracted as follows.

![design_idea.JPG](https://bitbucket.org/repo/nLdgk7/images/4131596285-design_idea.JPG)

### View:

This package is mainly composed of three basic panels (i.e. game panel, information panel and setting panel) and main frame window; 

### Model:

This package is mainly composed of puzzle model and block model, such that the GUI interface can be updated while the background calculations can be executed.

## Gaming Rule and Game Panel

The game panel is defined as the area where users are able to slide numbered block by left clicking relevant tiles.

The goal is to solve the shuffled number slider puzzle by moving blocks into empty space with multiple steps. An example size 4 unsolved and solved number slider puzzle can be referred as follows.

![logic.JPG](https://bitbucket.org/repo/nLdgk7/images/3676167309-logic.JPG)

After successfully finishing current round of game, a new game puzzle with increased puzzle size will be generated whose difficulty level is automatically enhanced. This process will continue until the puzzle size reaches 10 which are considered as the maximum size of this app.

## Information Panel

The features of various areas can be summarized as follows:

Item         | Prompted Info   | Notes 
:---------   | :-----          |:-----
Puzzle Size  | Number of row and column of generated square size puzzle. | This value can be modified by users which are suggested between 2 to 10 (the large value, the higher challenge).
Number of Shuffles     | Number of shuffles for generating puzzle.  | This value can be modified by users which are limited between 10 and 1010 (the large value, the higher challenge). And the value can be input by either typing or dragging slider.
Moves        | Numbers of moves users have been made.  |This value will be reset to 0 when reshuffled or start a new game.
Time Elapsed | Seconds elapsed since first move being made. | This value can be hid by clicking ‘Not Show Timing’, which may be recovered by clicking ‘Show Timing’.
Current Time | Display system date and time |
Available Memory | Indicate real-time memory information | This feature is designed for software developer for future application development.

## Control Panel and Menu Bar

The features of various areas can be summarized as follows:

Button           | Features   
:---------       | :----- 
Not Show Timing  |  Hide time elapsed information. 
Show Time        |   Show time elapsed information.
Reset/Reshuffle  |     Reshuffle current game without changing puzzle size.
Restart          | Reset puzzle size to 2 and restart the game.
Pause            |Pause the game temporary including elapsed timing feature.
Resume           |Resume the game from last state.
Exit             |Terminate and exit the program.
Save             |Output game result to a file and export the file to designated location.

## Exceptions

The possible exception condition which may occur due to unexpected reasons and operations such as wrong characters input can be summarized as follows. Several possible exception handler are implemented in the app.

![exceptions.JPG](https://bitbucket.org/repo/nLdgk7/images/278871972-exceptions.JPG)

## References:

[1] O. Astrachan (2003), "Course notes of MVC", Computer Science at Duke University, [Online], Available at: <[https://www.cs.duke.edu/courses/cps108/spring03/notes/slides10-4up.pdf](https://www.cs.duke.edu/courses/cps108/spring03/notes/slides10-4up.pdf)> [Accessed on 5 April 2016] 

[2] Google Developer (n.d.), “MVC Architecture”, [Online], Available at: <[https://developer.chrome.com/apps/app_frameworks](https://developer.chrome.com/apps/app_frameworks)> [Accessed on 5 April 2016]