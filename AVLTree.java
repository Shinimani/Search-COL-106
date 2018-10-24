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

  


}
