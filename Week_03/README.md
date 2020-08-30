学习笔记

### week3

1. #### [重复的子字符串](https://leetcode-cn.com/problems/repeated-substring-pattern/)

   比较容易看懂的思路：

   如果一个字符串s能由自己的子串s1组成，s = s1s1s1s1;那么 s+s = s1s1s1s1 s1s1s1s1,即使去除第一个和最后一个字符，也会包含s

   ```java
public static boolean repeatedSubstringPattern(String s) {
       return (s + s).indexOf(s, 1) != s.length();
}
   ```

   ***BM算法***

   采用尾部字符比较，字符串与搜索词头部对齐，从尾部开始搜索，如果遇到不匹配的记为坏字符，如果坏字符能在搜索词中找到，则将坏字符对齐，如果找不到，直接将搜索词头部移动到字符串的坏字符下一个位置。

   **坏字符规则**

   对齐字符串和搜索词的头部

   <img src="https://upload-images.jianshu.io/upload_images/3247424-2c684768711dba43.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp" alt="img" style="zoom:67%;" />

   坏字符S在搜索词中没有，则直接将搜索词的头部对齐到坏字符的下一个位置
   
   <img src="https://upload-images.jianshu.io/upload_images/3247424-a36131ba1a90fe5c.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp" alt="img" style="zoom:67%;" />
   
   坏字符P在搜索词中能找到，则将坏字符和搜索词中的该字符对齐
   

<img src="https://upload-images.jianshu.io/upload_images/3247424-99333aaee2a8ed39.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp" alt="img" style="zoom:67%;" />



> 1. 若搜索词中存在坏字符，则两者对齐
   >
> 2. 若搜索词中不存在坏字符，则直接移动到坏字符后
   >    用公式表示为：
   >
>    后移位数 = 坏字符的位置 - 搜索词中的上一次出现位置

按照坏字符规则，此时搜索词要后移 2 - （-1） =  3

**好后缀规则**

<img src="https://upload-images.jianshu.io/upload_images/3247424-99333aaee2a8ed39.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp" alt="img" style="zoom:67%;" />

> 好后缀规则：
   > 后移位数 = 好后缀的位置 - 搜索词中上一次出现位置

   此时，所有的"好后缀"（MPLE、PLE、LE、E）之中，只有"E"在"EXAMPLE"还出现在头部，所以后移 6 - 0 = 6位。

   "坏字符规则"只能移3位，"好后缀规则"可以移6位。所以，**Boyer-Moore算法的基本思想是，每次后移这两个规则之中的较大值。**

   <img src="https://img-blog.csdnimg.cn/20190117203341654.png" alt="å¨è¿éæå¥å¾çæè¿°" style="zoom:67%;" />

   此时，按照坏字符规则，将字符串中的P与搜索词中的P对齐，移动位数 6 - 4 = 2

   <img src="https://img-blog.csdnimg.cn/20190117203821784.png" alt="img" style="zoom:67%;" />




2. #### [递增子序列](https://leetcode-cn.com/problems/increasing-subsequences/)

   解题思路：

   枚举出所有可能的子序列，对数组中的每一个元素来说，都有被选中和不被选中的两种状态，所以一共有2^种情况，然后判断其中每个序列是否满足要求，同时还需要进行去重（使用哈希表，将字符串转成一个哈希值），这种方法需要枚举出每一种子序列并且进行判断，可以增加一些判断减少计算的次数：

   - 只有当前元素大于上一个选择的元素，才选择当前元素

   ```java
   List<List<Integer>> res = new ArrayList<>();
   List<Integer> temp = new ArrayList<>();
   public List<List<Integer>> findSubsequences(int[] nums) {
       dfs(0, Integer.MIN_VALUE, nums);
       return res;
   }
   private void dfs(int cur, int last, int[] nums) {
       //当指针到达数组的最后一个位置，并且temp满足要求时加入res中
       if (cur == nums.length) {
           if (temp.size() > 1) {
               res.add(new ArrayList<>(temp));
           }
           return;
       }
       //当前元素大于上一个元素，添加当前元素并进行下一次递归
       if (nums[cur] >= last) {
           temp.add(nums[cur]);
           dfs(cur + 1, nums[cur], nums);
           //回溯
           temp.remove(temp.size() - 1);
       }
       //去重
       if (nums[cur] != last) {
           dfs(cur + 1, last, nums);
       }
   }
   
   ```

3. #### [保证文件名唯一](https://leetcode-cn.com/problems/making-file-names-unique/)

   解题思路，将所有的文件名和文件名出现的次数存到hashMap中，然后遇到map中已有的名称，将数量加1，同时把新增的文件名也存入map中

   ```java
   public String[] getFolderNames(String[] names) {
       Map<String, Integer> map = new HashMap<>();
       String[] res = new String[names.length];
       for (int i = 0; i < names.length; i++) {
           if (!map.containsKey(names[i])) {
               res[i] = names[i];
               map.put(names[i], 1);
           } else {
               int count = map.get(names[i]);
               while (map.containsKey(names[i] + "(" + (count) + ")")){
                   count++;
               }
               res[i] = names[i] + "(" + (count) + ")";
               map.put(names[i] + "(" + (count) + ")", 0);
               map.put(names[i], map.get(names[i]) + 1);
           }
       }
       return res;
   }
   ```

4. #### [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)

   主要思路还是用递归，首先将所有的数字对应的字母存到哈希表中，开始递归，主要的参数path（当前选择的字符），每次递归主要改变该变量

   ```java
   public List<String> letterCombinations(String digits) {
       List<String> res = new ArrayList<>();
       if(digits.length() == 0){
           return new ArrayList<>();
       }
       Map<Character, String> map = new HashMap<>();
       map.put('2', "abc");
       map.put('3', "def");
       map.put('4', "ghi");
       map.put('5', "jkl");
       map.put('6', "mno");
       map.put('7', "pqrs");
       map.put('8', "tuv");
       map.put('9', "wxyz");
       StringBuilder path = new StringBuilder();
       dfs(res, path, digits, map);
       return res;
   }
   
   private void dfs(List<String> res, StringBuilder path, String digits, Map<Character, String> map) {
       //path的长度和字符串的长度相等，说明已经到字符串的末尾
       if (digits.length() == path.length()) {
           res.add(path.toString());
           return;
       }
       //每次获取一个字符
       int i = path.length();
       char c = digits.charAt(i);
       String letters = map.get(c);
       for (char l : letters.toCharArray()) {
           path.append(l);
           dfs(res, path, digits, map);
           //取消上一次的选择
           path.delete(path.length() - 1, path.length());
       }
   }
   ```

5. #### [超级次方](https://leetcode-cn.com/problems/super-pow/)

   解题思路

   (a * b) % k = (a % k)(b % k) %k，模运算

   pow(a, {1, 2, 3}) = pow(a, {3}) * pow(a, {1, 2})^10

   主要还是一些数学的解题方法，需要将大问题拆分成多个小问题

   

6. #### [重新安排行程](https://leetcode-cn.com/problems/reconstruct-itinerary/)

   从起点出发，进行深度优先搜索。

   每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。

   如果没有可移动的路径，则将所在节点加入到栈中，并返回。

   对于每一个当前节点而言，所有“非死胡同”的分支都会走回到该节点，而“死胡同”分支不会



7. #### [全排列 II](https://leetcode-cn.com/problems/permutations-ii/)

   回溯，剪枝

   ```java
   public List<List<Integer>> permuteUnique(int[] nums) {
       List<List<Integer>> res = new ArrayList<>();
       if(nums.length == 0){
           return res;
       }
       Arrays.sort(nums);
       boolean[] used = new boolean[nums.length];
       Deque<Integer> path = new ArrayDeque<>();
       dfs(res, path, nums, used, 0);
       return res;
   }
   
   private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] nums, boolean[] used, int depth) {
       if(depth == nums.length){
           res.add(new ArrayList<>(path));
           return;
       }
       for(int i = 0; i < nums.length; i++){
           if(used[i])
               continue;
           if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
               continue;
   
           path.addFirst(nums[i]);
           used[i] = true;
           dfs(res, path, nums, used, depth + 1);
           used[i] = false;
           path.removeFirst();
       }
   }
   ```

8. #### [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

   考虑通过递归对二叉树进行后序遍历，当遇到节点 p或 q时返回。从底至顶回溯，当节点 p, q 在节点 root 的异侧时，节点 root 即为最近公共祖先，则向上返回 root 。递归边界条件：越过了叶子结点或者找到了p或者q，先遍历左节点，返回值记为left，再遍历右节点，返回值记为right，当left和right同时为null，说明左右子树都不包含p，q，当left和right都不为空，说明p和q在root的两侧，当left为空，right不为空，说明p和q都在右子树中，返回right （***需要多做几次这道题***）

   ```java
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       //如果越过叶子节点，或者已经找到p或者q
       if(root == null || root == p || root == q){
           return root;
       }
       //先遍历左节点
       TreeNode left = lowestCommonAncestor(root.left, p, q);
       TreeNode right = lowestCommonAncestor(root.right, p, q);
       if(left == null)
           return right;
       if(right == null)
           return left;
       return root;
}
   ```

9. #### [最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/)

   ```java
   public int threeSumClosest(int[] nums, int target) {
       int n = nums.length;
       if (n < 3) {
           return 0;
       }
       int res = 0;
       int min = 1000;
       Arrays.sort(nums);
       for (int i = 0; i < n - 2; i++) {
           int left = i + 1, right = n - 1;
           while (right > left) {
               int sum = nums[i] + nums[left] + nums[right];
               int temp = Math.abs(sum - target);
               if (temp < min) {
                   min = temp;
                   res = sum;
               }
               if (sum > target) {
                   right--;
               } else if (sum < target) {
                   left++;
               } else {
                   return sum;
               }
           }
       }
       return res;
   }
   ```
   
   和三数之和的思路进本一样，先排序，三指针不断遍历

10. #### [第 k 个数](https://leetcode-cn.com/problems/get-kth-magic-number-lcci/)

    基本思路和丑数那个题一样，动态规划，dp[n]为这个数组，dp[0] = 1，dp[n] = min(dp[a] * 3, dp[b]*5, dp[c] * c)，当i为1的时候a，b，c均为0，当下一个数等于 dp[a] * 3时，a++

    ```java
    public int getKthMagicNumber(int k) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            int temp = Math.min(Math.min(dp[a] * 3, dp[b] * 5), dp[c] * 7);
            dp[i] = temp;
            if (dp[i] == dp[a] * 3 && dp[i] == dp[b] * 5 && dp[i] == dp[c] * 7) {
                a++;
                b++;
                c++;
            } else if (dp[i] == dp[a] * 3 && dp[i] == dp[b] * 5) {
                a++;
                b++;
            } else if (dp[i] == dp[b] * 5 && dp[i] == dp[c] * 7) {
                b++;
                c++;
            } else if (dp[i] == dp[a] * 3 && dp[i] == dp[c] * 7) {
                a++;
                c++;
            } else if (dp[i] == dp[a] * 3) {
                a++;
            } else if (dp[i] == dp[b] * 5) {
                b++;
            } else if (dp[i] == dp[c] * 7) {
                c++;
            }
        }
        return dp[k - 1];
    }
    ```