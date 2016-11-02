import java.util.*;

/**
 * @author Alyssa  on 10/15/2016.
 * https://open.kattis.com/problems/daceydice
 */
public class DaceyTheDice {
    //static RecursionState desiredState;
    private static int[][] nbrs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static int n;
    private static char[][] forestyx;
    private static Location homeLoc;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        StringBuilder sb = new StringBuilder();
        trial:
        for(int i = 0; i < t; i++) {
            HashMap<Location, HashSet<State>> seen = new HashMap<>();
            n = Integer.parseInt(sc.next());
            Deque<RecursionState> dfs = new ArrayDeque<>();
            forestyx = new char[n][n];
            Location startLoc = new Location(-1, -1);
            sc.nextLine();
            for(int j = 0; j < n; j++) {
                String line = sc.nextLine();
                int stIdx = line.indexOf('S');
                if(stIdx >= 0) {
                    startLoc = new Location(stIdx, j);
                }
                int hmIdx = line.indexOf('H');
                if(hmIdx >= 0) {
                    homeLoc = new Location(hmIdx, j);
                }
                forestyx[j] = line.toCharArray();
            }
            State curr = new State(1, 6, 4, 3, 5, 2);
            dfs.push(new RecursionState(curr, startLoc));
            while(!dfs.isEmpty()) {
                RecursionState r = dfs.pop();
                if(isDesiredState(r)) {
                    sb.append("Yes");
                    sb.append("\n");
                    continue trial;
                }
                else {
                    //if not seen at that location, or if it hasn't been seen in that state
                    if((seen.get(r.loc) == null)||(!seen.get(r.loc).contains(r.state))) {
                        //mark as seen
                        seen.putIfAbsent(r.loc, new HashSet<>());
                        seen.get(r.loc).add(r.state);
                        //mark as seen done

                        for (int[] nb : nbrs) {
                            int currX = r.loc.x + nb[0];
                            int currY = r.loc.y + nb[1];

                            if (isValid(currX, currY)) {
                                Location loc = new Location(currX, currY);
                                dfs.push(new RecursionState(r.state.moveTo(nb), loc));
                            }
                        }

                    }
                }


            }
            sb.append("No");
            sb.append("\n");



        }
        System.out.print(sb);
    }

    private static class Location {
        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 100 + y;
        }

        @Override
        public boolean equals(Object obj) {
            return obj.getClass().equals(this.getClass())&&this.hashCode() == obj.hashCode();
        }
    }

    private static boolean isDesiredState(RecursionState r)  {
        return(r.loc.x == homeLoc.x &&
               r.loc.y == homeLoc.y &&
               r.state.down == 5);
    }
    private static boolean isValid(int x, int y) {
        return x >= 0 &&
                y >= 0 &&
                x < n &&
                y < n &&
                forestyx[y][x] != '*';
    }


    private static class RecursionState {
        State state;
        Location loc;

        RecursionState(State state, Location xy) {
            this.state = state;
            this.loc = xy;
        }

        @Override
        public boolean equals(Object obj) {
            if(!obj.getClass().equals(this.getClass())) {
                return false;
            }
            RecursionState r = (RecursionState) obj;
            return (this.state.equals(r.state)&&(loc.x == r.loc.x)&&(loc.y == r.loc.y));
        }
    }

    private static class State{


        int top, bottom, up, down, left, right;

        State(int top, int bottom, int up, int down, int left, int right) {
            this.top = top;
            this.bottom = bottom;
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
            if((top + bottom != 7)||
                    (up + down != 7)||
                    (left + right != 7)) {
                System.out.println("ERROR CREATING DICE");
                System.exit(0);
            }

        }

        @Override
        public int hashCode() {
            return top  +  left * 10 + up * 100;
        }

        State moveTo(int[] m) {
            if(m[0] == -1 && m[1] == 0) return moveLeft();
            if(m[0] == 1 && m[1] == 0) return moveRight();
            if(m[0] == 0 && m[1] == -1) return moveDown();
            return moveUp();
        }

        State moveRight() {
            return new State(top, bottom, left, right, down, up);
        }

        State moveLeft() {
            return new State(top, bottom, right, left, up, down);
        }

        State moveUp() {
            return new State(up, down, bottom, top, left, right);
        }

        State moveDown() {
            return new State(down, up, top, bottom, left, right);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass() != State.class) {
                return false;
            }
            State s = (State) obj;
            return top == s.top &&
                    bottom == s.bottom &&
                    up == s.up &&
                    down == s.down &&
                    left == s.left &&
                    right == s.right;
        }
    }
}
