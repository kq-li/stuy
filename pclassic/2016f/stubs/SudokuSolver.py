class Sudoku:
    def __init__(self, puzzle):
        self.puzzle = {}

        for row in range(len(puzzle)):
            for col in range(len(puzzle[row])):
                self.puzzle[(row, col, self.getSquareNum(row, col))] = puzzle[row][col]

    def getRow(self, n):
        return [self.puzzle[cell] for cell in self.puzzle if cell[0] == n]

    def getColumn(self, n):
        return [self.puzzle[cell] for cell in self.puzzle if cell[1] == n]

    def getSquare(self, n):
        return [self.puzzle[cell] for cell in self.puzzle if cell[2] == n]

    def getSquareNum(self, row, col):
        return 3 * (row / 3) + (col / 3)

    def missingRow(self, n):
        row = self.getRow(n)
        ret = []

        for i in range(1, 10):
            if i not in row:
                ret.append(i)
                
        return ret

    def missingColumn(self, n):
        col = self.getColumn(n)
        ret = []

        for i in range(1, 10):
            if i not in col:
                ret.append(i)
                
        return ret

    def missingSquare(self, n):
        sq = self.getSquare(n)
        ret = []

        for i in range(1, 10):
            if i not in square:
                ret.append(i)
                
        return ret

    def genPossibilities(self):
        possibilities = {}
        
        for cell in self.puzzle:
            if self.puzzle[cell] == 0:
                row = self.getRow(cell[0])
                column = self.getColumn(cell[1])
                square = self.getSquare(cell[2])

                cellPossibilities = []

                for i in range(1, 10):
                    if i not in row and i not in column and i not in square:
                        cellPossibilities.append(i)

                if len(cellPossibilities) > 0:
                    possibilities[cell] = cellPossibilities

        return possibilities

    def eliminate(self):
        possibilities = self.genPossibilities()

        while len(possibilities) > 0:
            for cell in possibilities:
                possibility = possibilities[cell]
                if len(possibility) == 1:
                    self.puzzle[cell] = possibility[0]

            possibilities = self.genPossibilities()

    def getPuzzle(self):
        ret = [[0 for j in range(9)] for i in range(9)]

        for cell in self.puzzle:
            row = cell[0]
            column = cell[1]
            ret[row][column] = self.puzzle[cell]

        return ret

def sudoku_solver(puzzle):
    """

    :param puzzle: a 9 x 9 grid (list of lists) of integers denoting the unsolved puzzle
    :return: a 9 x 9 grid (list of lists) of integers denoting the solved puzzle
    """
    # TODO: implement
    sudoku = Sudoku(puzzle)
    sudoku.eliminate()
    return sudoku.getPuzzle()


def parse_file_and_call_function():
    with open("SudokuSolverIN.txt", "r") as f:
        puzzles = []
        current_puzzle = []
        for line in f:
            line = line.strip()
            if len(line) > 0:
                current_puzzle.append([int(x) for x in line.split()])
                if len(current_puzzle) == 9:
                    puzzles.append(current_puzzle)
                    current_puzzle = []

        for puzzle in puzzles:
            ans = sudoku_solver(puzzle)
            out = ''
            for line in ans:
                out += ' '.join([str(x) for x in line])
                out += '\n'
            print out


if __name__ == "__main__":
    parse_file_and_call_function()
