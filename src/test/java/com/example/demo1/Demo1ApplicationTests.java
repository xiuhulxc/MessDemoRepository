package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
    }

    static class Node {
        int values;
        Node left;
        Node right;

        public Node(int values){
            this.values = values;
            this.left = null;
            this.right = null;
        }

        public static class TreeSearch{

            public static Node searchBST(Node root,int target){
                if (root == null || root.values == target){
                    return root;
                }
                if (target < root.values){
                    return searchBST(root.left,target);
                }else {
                    return searchBST(root.right,target);
                }
            }


            public static void main(String[] args) {
                // 构建二叉搜索树
                Node root = new Node(5);
                root.left = new Node(3);
                root.right = new Node(7);
                root.left.left = new Node(2);
                root.left.right = new Node(4);
                root.right.left = new Node(6);
                root.right.right = new Node(8);

                Node node =  searchBST(root,4);
                if (node == null){
                    System.out.println("wu");
                }else {
                    System.out.println("1");
                    System.out.println(node);
                }
            }

        }


    }

}
