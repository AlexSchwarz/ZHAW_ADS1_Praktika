package Praktikum05;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    @Override
    public void inorder(Visitor<T> vis) {
        inorder(root, vis);
    }

    private void inorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            inorder(node.left, visitor);
            visitor.visit(node.element);
            inorder(node.right, visitor);
        }
    }

    @Override
    public void preorder(Visitor<T> vis) {
        preorder2(root, vis);
    }

    private void preorder2(TreeNode<T> node, Visitor<T> visitor) {
        if(node != null) {
            visitor.visit(node.element);
            preorder2(node.left, visitor);
            preorder2(node.right, visitor);
        }
    }

    @Override
    public void postorder(Visitor<T> vis) {
        postorder(root, vis);
    }

    private void postorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            postorder(node.left, visitor);
            postorder(node.right, visitor);
            visitor.visit(node.element);
        }
    }

    @Override
    public void levelorder(Visitor<T> vistor) {
        levelorder(root, vistor);
    }

    private void levelorder(TreeNode<T> node, Visitor<T> visitor) {
        Queue q = new LinkedList();
        if (node != null) q.add(node);
        while (!q.isEmpty()){
            node = (TreeNode<T>) q.remove();
            visitor.visit(node.element);
            if (node.left !=null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
    }

    @Override
    public void interval(T min, T max, Visitor<T> v) {
        interval(root, min, max, v);
    }

    private void interval(TreeNode<T> node, T min, T max, Visitor<T> v) {
        if (node != null) {
            if (node.element.compareTo(min) > -1 && node.element.compareTo(max) < 1) {
                v.visit(node.element);
                interval(node.left, min, max, v);
                interval(node.right, min, max, v);
            }
        }
    }

}