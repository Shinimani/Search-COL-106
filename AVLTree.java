class AVLNode
{
    AVLNode left, right;
    Position data;
    int height;

    /* Constructor */
  AVLNode(Position n)
    {
        left = null;
        right = null;
        data = n;
        height = 0;
    }

  public Position getData()
  {
    return this.data;
  }


}

public class AVLTree
{
  private AVLNode root;

  AVLTree()
  {
    root = null;
  }

  public boolean isEmpty()
  {
    return root==null;
  }

  public void insert(Position x)
  {
    root = insert(x, root);
  }

  public int height(AVLNode t )
  {
     return t == null ? -1 : t.height;
  }

  public int max(int x, int y)
  {
    return x > y ? x : y;
  }
  //
  // /* Rotate binary tree node with left child */
  //   private AVLNode rotateWithLeftChild(AVLNode k2)
  //   {
  //       AVLNode k1 = k2.left;
  //       k2.left = k1.right;
  //       k1.right = k2;
  //       k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
  //       k1.height = max( height( k1.left ), k2.height ) + 1;
  //       return k1;
  //   }
  //
  //   /* Rotate binary tree node with right child */
  //   private AVLNode rotateWithRightChild(AVLNode k1)
  //   {
  //       AVLNode k2 = k1.right;
  //       k1.right = k2.left;
  //       k2.left = k1;
  //       k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
  //       k2.height = max( height( k2.right ), k1.height ) + 1;
  //       return k2;
  //   }
  //   /**
  //    * Double rotate binary tree node: first left child
  //    * with its right child; then node k3 with new left child */
  //   private AVLNode doubleWithLeftChild(AVLNode k3)
  //   {
  //       k3.left = rotateWithRightChild( k3.left );
  //       return rotateWithLeftChild( k3 );
  //   }
  //   /**
  //    * Double rotate binary tree node: first right child
  //    * with its left child; then node k1 with new right child */
  //   private AVLNode doubleWithRightChild(AVLNode k1)
  //   {
  //       k1.right = rotateWithLeftChild( k1.right );
  //       return rotateWithRightChild( k1 );
  //   }
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

  public AVLNode insert(Position x, AVLNode k)
  {
    if (k==null)
    {
      k = new AVLNode(x);
    }
    else if (x.getWordIndex() <= k.getData().getWordIndex())
    {
    	k.left = insert (x, k.left);
    	if (height(k.left) - height(k.right) == 2)
    	{
    		if (x.getWordIndex() <= k.left.getData().getWordIndex())
    		{
    			k = rotateLL(k);
    		}
    		else
    		{
    			k = rotateRL(k);
    		}
    	}
    }
    else
    {
    	k.right = insert (x, k.right);
    	if (height(k.right) - height(k.left) == 2)
    	{
    		if (x.getWordIndex() > k.right.getData().getWordIndex())
    		{
    			k = rotateRR(k);
    		}
    		else
    		{
    			k = rotateLR(k);
    		}
    	}
    }

    k.height = max(height(k.left), height(k.right)) + 1;
    return k;
  }

  public AVLNode search(int wordIndex)
  {
	  return search(root, wordIndex);
  }
//same word index multiple times ho sakta hai, if ho to vo left child me hi hoga because <=
  public AVLNode search(AVLNode k, int wordIndex)
  {
	  boolean found = false;
	  while ((k != null) && !found)
	  {
		  int kwordIndex = k.getData().getWordIndex();
		  if (wordIndex < kwordIndex)
		  {
			  k = k.left;
		  }
		  else if (wordIndex > kwordIndex)
		  {
			  k = k.right;
		  }
		  else
		  {
			  found = true;
			  break;
		  }
	  }
	  if (found)
	  {
		  return k;
	  }
	  else return null;
  }
  
  //this is the search for the second type of word index, i.e. the index without the connecting words 
  public AVLNode searchWC(int wordIndex)
  {
	  return searchWC(root, wordIndex);
  }
//same word index multiple times ho sakta hai, if ho to vo left child me hi hoga because <=
  public AVLNode searchWC(AVLNode k, int wordIndex)
  {
	  boolean found = false;
	  while ((k != null) && !found)
	  {
		  int kwordIndex = k.getData().getWCIndex();
		  if (wordIndex < kwordIndex)
		  {
			  k = k.left;
		  }
		  else if (wordIndex > kwordIndex)
		  {
			  k = k.right;
		  }
		  else
		  {
			  found = true;
			  break;
		  }
	  }
	  if (found)
	  {
		  return k;
	  }
	  else return null;
  }
  
  
  

  public void inorder()
  {
      inorder(root);
  }
  private void inorder(AVLNode r)
  {
      if (r != null)
      {
          inorder(r.left);
          System.out.print("(" + r.data.getPageEntry().name + "," +r.data.getWordIndex() + ")"  +" ");
          inorder(r.right);
      }
  }

}
