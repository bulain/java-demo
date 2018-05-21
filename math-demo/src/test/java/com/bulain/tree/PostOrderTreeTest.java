package com.bulain.tree;

import org.junit.Test;

public class PostOrderTreeTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new PostOrderTree();
        traverse.traverse(tree);
        System.out.println();
    }

}
