# week2

1. 先回顾栈和队列的一些题目，滑动窗口，温度问题等利用单调栈来解决以减少时间复杂度

   ```java
   //每日温度
   public int[] dailyTemperatures(int[] te) {
       int n = te.length;
       int[] res = new int[n];
       Stack<Integer> stack = new Stack<>();
       for(int i = 0; i < n; i++){
           //维护一个单调栈，栈顶是最小值，栈底如果有值，说明还没有找到比栈底更大的值
           while (!stack.isEmpty() && te[stack.peek()] < te[i]){
               int preIndex = stack.pop();
               res[preIndex] = i - preIndex;
           }
           stack.push(i);
       }
       return res;
   }
   ```

2. #### [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)

   之前是用的双指针的方法，看了几遍用双端队列解题的思路，具体思路

   分两个阶段，窗口形成之前 i < k 和窗口左端已经进入数组 i >=k,维护一个单调的队列，保证队列顶端是最大值

   ```java
   public static int[] maxSlidingWindow2(int[] nums, int k) {
       Deque<Integer> deque = new LinkedList<>();
       int n = nums.length;
       int[] res = new int[n - k + 1];
       for (int i = 0; i < k; i++) {
           while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
               deque.removeLast();
           }
           deque.addLast(nums[i]);
       }
       res[0] = deque.peekFirst();
       for (int i = k; i < n; i++) {
           if (deque.peekFirst() == nums[i - k]) {
               deque.removeFirst();
           }
           while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
               deque.removeLast();//保证deque递减
           }
           deque.addLast(nums[i]);
           res[i - k + 1] = deque.peekFirst();
       }
       return res;
   }
   ```

3. #### [打家劫舍](https://leetcode-cn.com/problems/house-robber/) 力扣上的每日一题

   从最简单的开始，如果只有一家，最大金额就是dp[0],如果有两家，则最大金额为max(dp[0], dp[1])，

   如果大于两家则分为两种情况

   - 偷最后一家（第n家），则n-1家就不能偷，最大金额为dp[n-2] + nums[n];

   - 2.2 不偷最后一家，则最大金额就是dp[n - 1];

   ```java
   //动态规划
   public static int rob(int[] nums) {
       int n = nums.length;
       if (n == 0) {
           return 0;
       }
       int[] dp = new int[n];
       dp[0] = nums[0];
       dp[1] = Math.max(nums[0], nums[1]);
       for (int i = 2; i < n; i++) {
           dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
       }
       return dp[n - 1];
   }
   ```

   

4. [复原IP地址](https://leetcode-cn.com/problems/restore-ip-addresses/)

   看到这道题首先想到的是递归，但是看到题解有一个暴力循环的方法，发现这道题用循环写代码更简洁易懂	

   ```java
   //暴力循环
   public List<String> restoreIpAddresses2(String s) {
       List<String> res = new ArrayList<>();
       StringBuilder sb = new StringBuilder();
       for (int a = 1; a < 4; a++) {
           for (int b = 1; b < 4; b++) {
               for (int c = 1; c < 4; c++) {
                   for (int d = 1; d < 4; d++) {
                       if (a + b + c + d == s.length()) {
                           int n1 = Integer.parseInt(s.substring(0, a));
                           int n2 = Integer.parseInt(s.substring(a, a + b));
                           int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                           int n4 = Integer.parseInt(s.substring(a + b + c));
                           if (n1 < 256 && n2 < 256 && n3 < 256 && n4 < 256) {
                               sb.append(n1).append(".").append(n2).append(".").append(n3).append(".").append(n4);
                               if (sb.length() == s.length() + 3) {
                                   res.add(sb.toString());
                               }
                               sb = new StringBuilder();
                           }
   
                       }
                   }
               }
           }
       }
       return res;
   }
   ```

5. #### [丑数](https://leetcode-cn.com/problems/chou-shu-lcof/)

   刚看到题目时毫无头绪。。直接看题解，有动态规划和小根堆两种解法

   动态规划具体思路

   dp[n] 肯定是dp[a] * 2，dp[b] * 3，dp[c] * 5中的最小值， 先从最简单的情况开始，a,b,c都为0（数组的起点），第二个丑数为2 =1*2，此时a++,第三个丑数3=1 * 3，b++,第四个丑数5 = 1 * 5，c++，第五个丑数6 = 2 * 3 （dp[1] * 3, dp[2] * 2）即 a和b都要自增一次

   ```java
   //动态规划
   public int nthUglyNumber(int n) {
       int[] dp = new int[n];
       int a = 0, b = 0, c = 0;
       dp[0] = 1;
       for (int i = 1; i < n; i++) {
           dp[i] = Math.min(Math.min(2 * dp[a], 3 * dp[b]), 5 * dp[c]);
           if(dp[i] == 2 * dp[a])
               a++;
           if(dp[i] == 3 * dp[b])
               b++;
           if(dp[i] == 5 * dp[c])
               c++;
       }
       return dp[n - 1];
   }
   ```

   小根堆解法

   ```java
   //小根堆解法， 时间复杂度较高,不断往堆中添加丑数，直到个数大于等于n
   public int nthUglyNumber2(int n){
       int[] uglyNums = {2, 3, 5};
       PriorityQueue<Long> pq = new PriorityQueue<>();
       pq.add(1L);
       int count = 0;//记录弹出的数的个数
       while(true){
           long cut = pq.poll();
           count++;
           if(count >= n){
               return (int)cut;
           }
           for (int i = 0; i < uglyNums.length; i++) {
               if(!pq.contains(uglyNums[i] * cut)){
                   pq.add(uglyNums[i] * cut);
               }
           }
       }
   }
   ```

6. #### [ 前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/)

   具体思路，先将数组遍历一遍，将每个元素及其出现的次数记录在哈希表中，然后将所有的元素及其频次加入到堆中，堆自动将频次最大的元素推到堆顶，然后不停的取堆顶的值即可

   ```java
   //大顶堆解法
   public int[] topKFrequent(int[] nums, int k) {
       int[] res = new int[k];
       Map<Integer, Integer> map = new HashMap<>();
       for (int i = 0; i < nums.length; i++) {
           map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
       }
       //堆中存一个数组，第一位是数字，第二位是频次，根据频次排序
       PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
           @Override
           public int compare(int[] nums1, int[] nums2) {
               return nums2[1] - nums1[1];
           }
       });
       for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
           pq.add(new int[]{entry.getKey(), entry.getValue()});
       }
       int i = 0;
       while (i < k) {
           res[i] = pq.poll()[0];
           i++;
       }
       return res;
   }
   ```

   做该题的过程中有个问题（题目中已注明前k个元素的频次不相同），如果插入堆中的元素和堆中已有的元素相等的，二叉堆该怎么实现？按照二叉堆的定义，所有的父节点必须大于子节点