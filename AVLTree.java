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
  
    
//   /* Rotate binary tree node with left child */
//     private AVLNode rotateWithLeftChild(AVLNode k2)
//     {
//         AVLNode k1 = k2.left;
//         k2.left = k1.right;
//         k1.right = k2;
//         k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
//         k1.height = max( height( k1.left ), k2.height ) + 1;
//         return k1;
//     }
  
//     /* Rotate binary tree node with right child */
//     private AVLNode rotateWithRightChild(AVLNode k1)
//     {
//         AVLNode k2 = k1.right;
//         k1.right = k2.left;
//         k2.left = k1;
//         k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
//         k2.height = max( height( k2.right ), k1.height ) + 1;
//         return k2;
//     }
//     /**
//      * Double rotate binary tree node: first left child
//      * with its right child; then node k3 with new left child */
//     private AVLNode doubleWithLeftChild(AVLNode k3)
//     {
//         k3.left = rotateWithRightChild( k3.left );
//         return rotateWithLeftChild( k3 );
//     }
//     /**
//      * Double rotate binary tree node: first right child
//      * with its left child; then node k1 with new right child */
//     private AVLNode doubleWithRightChild(AVLNode k1)
//     {
//         k1.right = rotateWithLeftChild( k1.right );
//         return rotateWithRightChild( k1 );
//     }

// private AVLNode rotateLL(AVLNode ntop)
// {
//     AVLNode 
// }


  


}
