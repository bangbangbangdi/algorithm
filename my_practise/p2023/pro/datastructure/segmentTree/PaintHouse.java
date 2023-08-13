package my_practise.p2023.pro.datastructure.segmentTree;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.datastructure.segmentTree
 * className:      PaintHouse
 * author:     BangDi
 * description:
 * 1.在1~N范围内有N个房子,有C种可以粉刷的颜色(C < 32),一开始所有房间都是无色
 * 2.可以调用paint(int L,int R,int C) 方法将L～R范围内的房子刷为C颜色
 * 3.可以调用query(int L,int R) 查询在L～R范围内有多少种不同的颜色
 * date:    2023/8/13 16:04
 * version:    1.0
 */
public class PaintHouse {

    public static class SegmentTree {
        private int[] color;
        private boolean[] change;

        public SegmentTree(int n) {
            int N = n + 1;
            color = new int[N << 2];
            change = new boolean[N << 2];
        }

        public void pushUp(int rt) {
            color[rt] = color[rt << 1] | color[rt << 1 | 1];
        }

        public void pushDown(int rt) {
            if (change[rt]) {
                change[rt << 1] = true;
                change[rt << 1 | 1] = true;
                color[rt << 1] = color[rt];
                color[rt << 1 | 1] = color[rt];
                change[rt] = false;
            }
        }

        public void paint(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                color[rt] = C;
                change[rt] = true;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            if (L <= mid) {
                paint(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                paint(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return color[rt];
            }
            int mid = (l + r) >> 1;
            int color = 0;
            if (L <= mid) {
                color = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                color |= query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return color;
        }
    }

}
