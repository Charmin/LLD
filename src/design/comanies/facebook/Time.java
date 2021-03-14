package design.comanies.facebook;

/**
 * // PROBLEM STATEMENT:
 * //
 * // Given a set of tasks, write a function to calculate the total time needed for a particular task to complete.
 * // The total time means the time of the task plus the time of its dependencies.
 *
 * // ID time dependencies
 * // 1  20   [2, 3]
 * // 2  10   [4]
 * // 3  5    []
 * // 4  5    []
 * //
 * // Example: If we take Task with id 1:
 * // task 1 = 20 + 10 + 5 + 5 = 40
 * // ========================== END OF PROBLEM STATEMENT ==========================
 *
 * // Below is MY Solution using BFS, initially I propsed Topological Sort (Wrong), I tried to write a DFS solution, but
 * // the interviewer said an Iterative solution was a good option.
 * //
 * // The structure above is clearly a Graph, and the easier way to solve the problem iteratively and within
 * // a 10 minutes time frame is using BFS! (I think!)
 */
public class Time {
}
