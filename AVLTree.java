import java.util.Scanner;

class AVLNode<T>
{
    AVLNode left, right;
    T data;
    int height;

    /* Constructor */
  AVLNode(T n)
    {
        left = null;
        right = null;
        data = n;
        height = 0;
    }
}

public class AVLTree<T>
{
  private AVLNode<T> root;

  AVLTree()
  {
    root = null;
  }

  public boolean isEmpty()
  {
    return root==null;
  }

  public void insertAtRoot(T x)
  {
    root = insert(data, root);
  }

  public int height(AVLNode t )
  {
     return t == null ? -1 : t.height;
  }

  public int max(int x, int y)
  {
    return x > y ? x : y;
  }
  
  private AVLNode rotateLL(AVLNode nodeTop)
  {
    AVLNode nodeLC = nodeTop.left;
    nodeTop.left = nodeLC.right;
    nodeLC.right = nodeTop;
    nodeTop.height = max(height(nodeTop.left), height(nodeTop.right)) + 1;
    nodeLC.height = max (height(nodeLC.right), nodeTop.height) + 1;
    return nodeLC;
  }

  private AVLNode rotateRR(AVLNode nodeTop)
  {
    AVLNode nodeRC = nodeTop.right;
    nodeTop.right = nodeRC.left;
    nodeRC.left = nodeTop;
    nodeTop.height = max(height(nodeTop.left), height(nodeTop.right)) + 1;
    nodeRC.height = max(height(nodeTop) , height(nodeRC.right)) + 1;
    return nodeRC;
  }

  private AVLNode rotateRL(AVLNode top)
  {
    top.left = rotateRR(top.left);
    return rotateLL(top);
  }

  private AVLNode rotateLR(AVLNode top)
  {
    top.right = rotateLL(top.right);
    return rotateRR(top);
  }

  

  


}
