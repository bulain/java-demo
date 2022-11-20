package com.bulain.tree;

import org.junit.jupiter.api.Test;

public class PostOrderLoopTreeTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new PostOrderLoopTree();
        traverse.traverse(tree);
        System.out.println();
    }

}
