package ru.itis;

import javax.transaction.TransactionRequiredException;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private class TreeNode {
        private T value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(T value) {
            this.value = value;
        }
    }



    private TreeNode root;

    public BinarySearchTreeImpl() {
        this.root = null;
    }

    @Override
    public void insert(T element) {
        this.root = insert(root, element);
    }

    private TreeNode insert(TreeNode root, T element) {
        if (root == null) {
            root = new TreeNode(element);
        } else if (root.value.compareTo(element) >= 0) {
            root.left = insert(root.left, element);
        } else {
            root.right = insert(root.right, element);
        }
        return root;
    }

    public TreeNode nextLeft() {
        return this.root.left;
    }

    public TreeNode nextRight() {
        return this.root.right;
    }

    @Override
    public boolean remove(T element) {
        if(contains(element)){
            if(root.value.compareTo(element) == 0){
                this.root = replaceNodes(this.root);
            }else {
                TreeNode prevNode = getPrevNode(this.root, element);
                if(prevNode.left != null && prevNode.left.value.compareTo(element) == 0){
                    prevNode.left = replaceNodes(prevNode.left);
                }else
                    prevNode.right = replaceNodes(prevNode.right);
            }
            return true;
        }else return false;
    }

    private TreeNode replaceNodes(TreeNode node) {
        if(node.left != null) {
            TreeNode temp = node.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = node.right;
            return node.left;
        }else return node.right;
    }


    private TreeNode getPrevNode(TreeNode root, T element) {
        if(root.left != null) {
            if (root.left.value.compareTo(element) == 0) {
                return root;
            } else if (root.value.compareTo(element) >= 0) {
                return getPrevNode(root.left, element);
            } else
                return getPrevNode(root.right, element);
        }else if(root.right != null){
            if (root.right.value.compareTo(element) == 0) {
                return root;
            } else if (root.value.compareTo(element) >= 0) {
                return getPrevNode(root.left, element);
            } else
                return getPrevNode(root.right, element);
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(TreeNode node, T element) {
        if(node == null)
            return false;
        if (node.value.compareTo(element) == 0) {
            return true;
        } else if (node.value.compareTo(element) >= 0) {
            return contains(node.left, element);
        } else {
            return contains(node.right, element);
        }
    }

    @Override
    public void print() {
        print(this.root);
    }

    private void print(TreeNode root) {
        if (root != null) {
            print(root.left);
            System.out.print(root.value + " ");
            print(root.right);
        }
    }

    @Override
    public void printByLevels() {
        int i = 0;
        while (printByLevels(this.root, i)) {
            System.out.println();
            i++;
        }
    }

    private boolean printByLevels(TreeNode root, int i) {
        if(root != null){
            if (i == 0) {
                System.out.print(root.value + " ");
                return true;
            }else{
                boolean f1 = printByLevels(root.left, i-1);
                boolean f2 = printByLevels(root.right, i - 1);
                return f1 || f2;
            }
        }
        return false;
    }
}