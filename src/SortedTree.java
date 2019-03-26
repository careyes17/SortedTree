class SortedTree {

  public final static int SIZE_INPUT = 8;
  TreeNode root;

  /**
   * Receives a sorted sequence of integers to build a binary tree. Given any node nd in this binary
   * tree, let its subtrees be denoted t1 and t2. The number of nodes in t1 and t2 differ by no more
   * than one.
   */
  public SortedTree(int[] input) {
    root = null;
    if (!verifyInput(input)) {//if input is not sorted return
      return;
    } else {
      root = buildTree(input, 0, input.length - 1);
    }
  }

  /**
   * Builds a binary using the objects from input[start] to input[end].
   *
   * See the constructor for the requirements for this binary tree
   */
  private TreeNode buildTree(int[] input, int start, int end) {

    // if the root node does not have any more children
    if (start > end) {
      return null;
    }

    int mid =
        (start + end) / 2; // calculates middle of the input array (odd/even #s are irrelevant)
    TreeNode root = new TreeNode(input[mid]); // makes node for middle of the array
    root.left = buildTree(input, start, mid - 1); // iterate for left child (left to the mid)
    root.right = buildTree(input, mid + 1, end); // iterate for right child (right to the mid)

    return root;
  }


  private boolean verifyInput(int[] input) {
    if (input == null) {
      return false;
    }

    for (int i = 0; i < input.length - 1; i++) {
      if (input[i] >= input[i + 1]) {
        return false;
      }
    }

    return true;
  }

  public int depth() {
    return _depth(root);
  }

  public int _depth(TreeNode root) {

    // if this root doesn't exist
    if (root == null) {
      return -1;
    }

    int leftDepth = _depth(root.left); // iterate for left child
    int rightDepth = _depth(root.right); // iterate for right child

    // select which child's depth is greater, then return it incremented
    if (leftDepth > rightDepth) {
      return (leftDepth + 1);
    } else {
      return (rightDepth + 1);
    }

  }

  public void traverse() {
    traverseNodes(root);
  }

  private void traverseNodes(TreeNode root) {

    // if this root doesn't exist
    if (root == null) {
      return;
    }

    traverseNodes(root.left); // iterate for left child
    System.out.print(root.info + " "); // prints root inorder
    traverseNodes(root.right); // iterate for right child

  }


  public static void main(String args[]) {

    int[] input = new int[SIZE_INPUT];
    for (int i = 0; i < SIZE_INPUT; i++) {
      input[i] = i;
    }

    //another input array:
    //int[] input = {4, 9, 15, 20, 22, 24, 35, 87};

    //1 - create the binary search tree given the sorted input
    SortedTree st = new SortedTree(input);

    //2 - print its depth
    System.out.println("The depth of the tree is " + st.depth());

    //3 - print the tree nodes in ascending order
    st.traverse();
  }
}
