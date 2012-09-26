# Product Backlog IV

## 1. "Austerity Measures"

A new study promoted by the government shows that mining robots consume a lot of energy. Austerity measurements must be enacted that forces the robot to collect all the diamonds (`x`) spending the *least possible fuel*. Your new job is to make the autopilot clever enough to maximize a score given by the following rules:

* 25 points gained for every diamond collected
* 1 point lost for every move made
* 50 extra points per diamond collected on reaching the open lift (`L`)

## 2. "Finding the Best Contractor"

In order to test the AI of each contractors' implementation, submitted programs should read a map description from standard input, and send a sequence of commands to standard output. The map description consists of `m` lines of up to `n` characters, describing the mine from top to bottom (i.e. the first line in the file is the row `m`, the last line is row `1`) then left to right (i.e. the first character in each line is column `1`). Characters represent the contents of cells as described above, i.e.:

    ###############
    #***...R......#
    #***... ...*..#
    #xxx... ..xxx.#
    #...... ...*..#
    #..     .. ...#
    #.... .... ...#
    #.... .... ...#
    #.. .       ..#
    #..*. .. .....#
    #.... .. .....#
    #.x.. .......*#
    #.............#
    #.........   .#
    #############L#

The best known solution for the above mine is `DDDLLLLLLURRRRRRRRRRRRDDDDDDDLLLLLLLLLLLDDDRRRRRRRRRRRD`.