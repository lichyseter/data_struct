package com.lichy.algorithm.stack;

import java.util.Stack;

/**
 * 利用stack来实现迷宫算法。
 * #为墙不可穿过。*为已走过路径。@为已走过，不能到达目标的位置。
 *
 * @author lichy
 */
public class MazeTest {

    /**
     * 迷宫。
     */
    private static char[][] array = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#', '#', ' ', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    public static void main(String[] args) {
        // 起始点为（1,1）,终止点为(8,8)
        final int startX = 1, startY = 1, endX = 8, endY = 8;
        Point cur = new Point(startX, startY, 1);
        Stack<Point> route = new Stack<>();
        do {
            if (cur.getX() == endX && cur.getY() == endY) {
                array[cur.getX()][cur.getY()] = '*';
                break;
            }
            char value = array[cur.getX()][cur.getY()];
            if (value == ' ') {
                route.push(cur);
                array[cur.getX()][cur.getY()] = '*';
                cur = nextPos(cur, 1);
            } else if (value == '#' || value == '@' || value == '*') {
                cur = route.pop();
                if (cur.getDirection() >= 4) {
                    array[cur.getX()][cur.getY()] = '@';
                    cur = route.pop();
                    if (cur.getDirection() <= 4) {
                        route.push(cur);
                    }
                } else {
                    cur.setDirection(cur.getDirection() + 1);
                    route.push(cur);
                    cur = nextPos(cur, cur.getDirection());
                }
            }
        } while (!route.isEmpty());

        for (char[] anArray : array) {
            for (char anAnArray : anArray) {
                System.out.print((anAnArray == '@' ? " " : anAnArray) + "，");
            }
            System.out.println();
        }

    }

    /**
     * 坐标
     */
    static class Point {
        /**
         * 横坐标
         */
        int x;
        /**
         * 纵坐标
         */
        int y;
        /**
         * 方向
         */
        int direction = 1;

        int getDirection() {
            return direction;
        }

        void setDirection(int direction) {
            this.direction = direction;
        }

        Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

    }

    /**
     * 向某个方向移动一个位置
     *
     * @param cur 位置
     * @return 下一个位置
     */
    static Point nextPos(Point cur, int direction) {
        int x = 0, y = 0;
        switch (direction) {
            case 1:
                x = cur.getX();
                y = cur.getY() + 1;
                break;//向右
            case 2:
                x = cur.getX() + 1;
                y = cur.getY();
                break;//向下
            case 3:
                x = cur.getX();
                y = cur.getY() - 1;
                break;//向左
            case 4:
                x = cur.getX() - 1;
                y = cur.getY();
                break;//向上
        }
        return new Point(x, y, 1);
    }

}
