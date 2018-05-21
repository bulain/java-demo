package com.bulain.antlr.util;

import antlr.ASTFactory;
import antlr.collections.AST;
import antlr.collections.impl.ASTArray;

public class ASTUtil {
    public static AST createParent(ASTFactory factory, int parentType, String parentText, AST child) {
        ASTArray array = createAstArray( factory, 2, parentType, parentText, child );
        return factory.make( array );
    }
    private static ASTArray createAstArray(ASTFactory factory, int size, int parentType, String parentText, AST child1) {
        ASTArray array = new ASTArray( size );
        array.add( factory.create( parentType, parentText ) );
        array.add( child1 );
        return array;
    }
}
