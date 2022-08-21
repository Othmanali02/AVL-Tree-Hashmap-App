package application;

public class AVLTree<T extends Comparable<T>> {
	AVLNode<T> root = null;

	public void ConstructAVLTree() {
		root = null;
	}

	// create removeAll() method to make AVL Tree empty
	public void removeAll() {
		root = null;
	}

	// create checkEmpty() method to check whether the AVL Tree is empty or not
	public boolean isEmpty() {
		if (root == null)
			return true;
		else
			return false;
	}

	// create insertElement() to insert element to to the AVL Tree
	public void insertElement(T element) {
		root = insertElement(element, root);
	}

	// create getHeight() method to get the height of the AVL Tree
	private int getHeight(AVLNode<T> node) {
		if (node == null)
			return -1;
		else
			return node.height;
	}

	// create maxNode() method to get the maximum height from left and right node
	private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {

		return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
	}

	// create insertElement() method to insert data in the AVL Tree recursively
	private AVLNode<T> insertElement(T element, AVLNode<T> node) {
		// check whether the node is null or not
		// insert a node in case when the given element is lesser than the element of
		// the root node
		if (node == null)
			node = new AVLNode<T>(element);
		else if (element.compareTo(node.data) < 0) {
			node.left = insertElement(element, node.left);
			if (getHeight(node.left) - getHeight(node.right) == 2)
				if (element.compareTo(node.left.data) < 0)
					node = rotateWithLeftChild(node);
				else
					node = doubleWithLeftChild(node);
		} else if (element.compareTo(node.data) > 0) {
			node.right = insertElement(element, node.right);
			if (getHeight(node.right) - getHeight(node.left) == 2)
				if (element.compareTo(node.right.data) > 0)
					node = rotateWithRightChild(node);
				else
					node = doubleWithRightChild(node);
		} else
			; // if the element is already present in the tree, we will do nothing
		node.height = getMaxHeight(getHeight(node.left), getHeight(node.right)) + 1;

		return node;

	}

	// creating rotateWithLeftChild() method to perform rotation of binary tree node
	// with left child
	private AVLNode<T> rotateWithLeftChild(AVLNode<T> node2) {
		AVLNode<T> node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		node2.height = getMaxHeight(getHeight(node2.left), getHeight(node2.right)) + 1;
		node1.height = getMaxHeight(getHeight(node1.left), node2.height) + 1;
		return node1;
	}

	// creating rotateWithRightChild() method to perform rotation of binary tree
	// node with right child
	private AVLNode<T> rotateWithRightChild(AVLNode<T> node1) {
		AVLNode<T> node2 = node1.right;
		node1.right = node2.left;
		node2.left = node1;
		node1.height = getMaxHeight(getHeight(node1.left), getHeight(node1.right)) + 1;
		node2.height = getMaxHeight(getHeight(node2.right), node1.height) + 1;
		return node2;
	}

	// create doubleWithLeftChild() method to perform double rotation of binary tree
	// node. This method first rotate the left child with its right child, and after
	// that, node3 with the new left child
	private AVLNode<T> doubleWithLeftChild(AVLNode<T> node3) {
		node3.left = rotateWithRightChild(node3.left);
		return rotateWithLeftChild(node3);
	}

	// create doubleWithRightChild() method to perform double rotation of binary
	// tree node. This method first rotate the right child with its left child and
	// after that node1 with the new right child
	private AVLNode<T> doubleWithRightChild(AVLNode<T> node1) {
		node1.right = rotateWithLeftChild(node1.right);
		return rotateWithRightChild(node1);
	}

	// create getTotalNumberOfNodes() method to get total number of nodes in the AVL
	// Tree
	public int getTotalNumberOfNodes() {
		return getTotalNumberOfNodes(root);
	}

	private int getTotalNumberOfNodes(AVLNode<T> head) {
		if (head == null)
			return 0;
		else {
			int length = 1;
			length = length + getTotalNumberOfNodes(head.left);
			length = length + getTotalNumberOfNodes(head.right);
			return length;
		}
	}

	// create searchElement() method to find an element in the AVL Tree
	public boolean searchElement(T element) {
		return searchElement(root, element);
	}

	private boolean searchElement(AVLNode<T> head, T element) {
		boolean check = false;
		while ((head != null) && !check) {
			T headElement = head.data;
			if (element.compareTo(headElement) < 0)
				head = head.left;
			else if (element.compareTo(headElement) > 0)
				head = head.right;
			else {
				check = true;
				break;
			}
			check = searchElement(head, element);
		}
		return check;
	}

	public int getBalance(AVLNode<T> N) {
		if (N == null)
			return 0;
		return getHeight(N.left) - getHeight(N.right);
	}

	public void delete(T element) {
		root = deleteNode(root, element);
	}

	public AVLNode<T> deleteNode(AVLNode<T> root, T key) {
		// STEP 1: PERFORM STANDARD BST DELETE
		if (root == null)
			return root;

		// If the key to be deleted is smaller than
		// the root's key, then it lies in left subtree
		if (key.compareTo(root.data) < 0)
			root.left = deleteNode(root.left, key);

		// If the key to be deleted is greater than the
		// root's key, then it lies in right subtree
		else if (key.compareTo(root.data) > 0)
			root.right = deleteNode(root.right, key);

		// if key is same as root's key, then this is the node
		// to be deleted
		else {

			// node with only one child or no child
			if ((root.left == null) || (root.right == null)) {
				AVLNode<T> temp = null;
				if (temp == root.left)
					temp = root.right;
				else
					temp = root.left;

				// No child case
				if (temp == null) {
					temp = root;
					root = null;
				} else // One child case
					root = temp; // Copy the contents of
									// the non-empty child
			} else {
				
				// node with two children: Get the inorder
				// successor (smallest in the right subtree)
				AVLNode<T> temp = minValueNode(root.right);

				// Copy the inorder successor's data to this node
				root.data = temp.data;

				// Delete the inorder successor
				root.right = deleteNode(root.right, temp.data);
			}
		}

		// If the tree had only one node then return
		if (root == null)
			return root;

		// STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

		// STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = getBalance(root);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && getBalance(root.left) >= 0)
			return rotateWithLeftChild(root);

		// Left Right Case
		if (balance > 1 && getBalance(root.left) < 0) {
			root.left = rotateWithRightChild(root.left);
			return rotateWithLeftChild(root);
		}

		// Right Right Case
		if (balance < -1 && getBalance(root.right) <= 0)
			return rotateWithRightChild(root);

		// Right Left Case
		if (balance < -1 && getBalance(root.right) > 0) {
			root.right = rotateWithLeftChild(root.right);
			return rotateWithRightChild(root);
		}

		return root;
	}

	public AVLNode<T> minValueNode(AVLNode<T> node) {
		AVLNode<T> current = node;

		/* loop down to find the leftmost leaf */
		while (current.left != null)
			current = current.left;

		return current;
	}

	public AVLNode<T> maxValueNode(AVLNode<T> node) {
		AVLNode<T> current = node;

		/* loop down to find the leftmost leaf */
		while (current.right != null)
			current = current.right;

		return current;
	}

	public int TreeHeight() {
		return maxDepth(root);
	}

	int maxDepth(AVLNode<T> node)
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }

	// create inorderTraversal() method for traversing AVL Tree in in-order form
	public void inorderTraversal() {
		inorderTraversal(root);
	}

	private void inorderTraversal(AVLNode<T> head) {
		if (head != null) {
			inorderTraversal(head.left);
			System.out.print(head.data + " ");
			inorderTraversal(head.right);
		}
	}

	public String InorderTraversal() {
		return InorderTraversal(root);
	}

	private String InorderTraversal(AVLNode<T> head) {
		String result = "";
		if (head != null) {
			result += InorderTraversal(head.left);
			result += head.data + " ";
			result += InorderTraversal(head.right);
		}
		return result;
	}

	// create preorderTraversal() method for traversing AVL Tree in pre-order form
	public String preorderTraversal() {
		return preorderTraversal(root);
	}

	private String preorderTraversal(AVLNode<T> head) {
		String result = "";
		if (head != null) {
			result += head.data + " ";
			result += preorderTraversal(head.left);
			result += preorderTraversal(head.right);
		}
		return result;
	}

	// create postorderTraversal() method for traversing AVL Tree in post-order form
	public String postorderTraversal() {
		return postorderTraversal(root);
	}

	private String postorderTraversal(AVLNode<T> head) {
		String result = "";
		if (head != null) {
			result += postorderTraversal(head.left);
			result += postorderTraversal(head.right);
			result += head.data + " ";
		}
		return result;
	}

}
