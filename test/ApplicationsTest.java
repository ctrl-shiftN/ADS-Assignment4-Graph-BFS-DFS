import applications.MazeSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ApplicationsTest {
    
    @Test
    public void testMazeSolverCellEquality() {
        MazeSolver.Cell cell1 = new MazeSolver.Cell(1, 2);
        MazeSolver.Cell cell2 = new MazeSolver.Cell(1, 2);
        MazeSolver.Cell cell3 = new MazeSolver.Cell(2, 1);
        
        assertEquals(cell1, cell2);
        assertNotEquals(cell1, cell3);
        assertEquals(cell1.hashCode(), cell2.hashCode());
    }
    
    @Test
    public void testMazeSolverSimpleMaze() {
        // Simple 3x3 maze
        char[][] maze = {
            {'S', '.', '.'},
            {'#', '#', '.'},
            {'.', '.', 'T'}
        };
        
        List<MazeSolver.Cell> path = MazeSolver.solveMaze(maze);
        
        // Should find a path
        assertFalse(path.isEmpty());
        assertEquals('S', maze[path.get(0).row][path.get(0).col]);
        assertEquals('T', maze[path.get(path.size()-1).row][path.get(path.size()-1).col]);
    }
    
    @Test
    public void testMazeSolverNoPath() {
        // Maze with no path
        char[][] maze = {
            {'S', '#', '.'},
            {'#', '#', '.'},
            {'.', '#', 'T'}
        };
        
        List<MazeSolver.Cell> path = MazeSolver.solveMaze(maze);
        
        // Should return empty list
        assertTrue(path.isEmpty());
    }
    
    @Test
    public void testStressTestPreparation() {
        // This would test performance on larger mazes
        char[][] largeMaze = new char[50][50];
        
        // Fill with simple pattern
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                largeMaze[i][j] = '.';
            }
        }
        
        largeMaze[0][0] = 'S';
        largeMaze[49][49] = 'T';
        
        // Just verify it runs without error
        List<MazeSolver.Cell> path = MazeSolver.solveMaze(largeMaze);
        assertNotNull(path);
        
        System.out.println("Stress test: Solved 50x50 maze");
    }
}
