package org.eclipse.epsilon.egl.parse;

// $ANTLR 3.1b1 EgxParserRules.g 2020-04-25 02:28:00

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 * -----------------------------------------------------------------------------
 * ANTLR 3 License
 * [The "BSD licence"]
 * Copyright (c) 2005-2008 Terence Parr
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *   derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
public class Egx_EgxParserRules extends org.eclipse.epsilon.common.parse.EpsilonParser {
    public static final int T__144=144;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int MODELDECLARATIONPARAMETER=74;
    public static final int T__145=145;
    public static final int BREAKALL=40;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int VAR=49;
    public static final int MODELDECLARATIONPARAMETERS=73;
    public static final int T__141=141;
    public static final int THROW=54;
    public static final int EGXMODULE=89;
    public static final int PARAMLIST=25;
    public static final int EXPRLIST=55;
    public static final int EXPRRANGE=56;
    public static final int BREAK=39;
    public static final int ELSE=32;
    public static final int T__137=137;
    public static final int T__136=136;
    public static final int FORMAL=24;
    public static final int IF=31;
    public static final int MultiplicativeExpression=58;
    public static final int TYPE=66;
    public static final int T__139=139;
    public static final int T__138=138;
    public static final int T__133=133;
    public static final int T__132=132;
    public static final int T__135=135;
    public static final int T__134=134;
    public static final int T__131=131;
    public static final int NewExpression=48;
    public static final int T__130=130;
    public static final int CASE=36;
    public static final int Letter=16;
    public static final int LINE_COMMENT=22;
    public static final int T__129=129;
    public static final int T__126=126;
    public static final int JavaIDDigit=18;
    public static final int T__125=125;
    public static final int LAMBDAEXPR=65;
    public static final int MAP=76;
    public static final int T__128=128;
    public static final int T__127=127;
    public static final int T__166=166;
    public static final int T__165=165;
    public static final int T__168=168;
    public static final int DOMAIN=86;
    public static final int T__167=167;
    public static final int T__162=162;
    public static final int T__161=161;
    public static final int MERGE=88;
    public static final int T__164=164;
    public static final int MODELDECLARATION=69;
    public static final int T__163=163;
    public static final int EXPRESSIONINBRACKETS=60;
    public static final int T__160=160;
    public static final int TERNARY=33;
    public static final int TRANSACTION=42;
    public static final int FLOAT_TYPE_SUFFIX=7;
    public static final int ITEMSELECTOR=75;
    public static final int COMMENT=21;
    public static final int ModelElementType=46;
    public static final int IMPORT=68;
    public static final int DELETE=53;
    public static final int ARROW=11;
    public static final int T__159=159;
    public static final int T__158=158;
    public static final int T__155=155;
    public static final int SPECIAL_ASSIGNMENT=27;
    public static final int T__154=154;
    public static final int T__157=157;
    public static final int T__156=156;
    public static final int T__151=151;
    public static final int T__150=150;
    public static final int T__153=153;
    public static final int T__152=152;
    public static final int Annotation=23;
    public static final int CONTINUE=41;
    public static final int ENUMERATION_VALUE=67;
    public static final int OPERATOR=59;
    public static final int EXPONENT=6;
    public static final int STRING=14;
    public static final int T__148=148;
    public static final int T__147=147;
    public static final int T__149=149;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int NAMESPACE=70;
    public static final int TARGET=84;
    public static final int T__92=92;
    public static final int COLLECTION=43;
    public static final int NEW=50;
    public static final int EXTENDS=81;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int PRE=79;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int POST=80;
    public static final int T__90=90;
    public static final int ALIAS=71;
    public static final int DRIVER=72;
    public static final int KEYVAL=77;
    public static final int POINT_POINT=10;
    public static final int GUARD=82;
    public static final int T__99=99;
    public static final int TEMPLATE=85;
    public static final int T__95=95;
    public static final int HELPERMETHOD=28;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int StatementBlock=29;
    public static final int T__98=98;
    public static final int T__177=177;
    public static final int T__176=176;
    public static final int ABORT=44;
    public static final int T__173=173;
    public static final int StrangeNameLiteral=15;
    public static final int T__172=172;
    public static final int T__175=175;
    public static final int T__174=174;
    public static final int FOR=30;
    public static final int BLOCK=63;
    public static final int T__171=171;
    public static final int T__170=170;
    public static final int PARAMETERS=47;
    public static final int SpecialNameChar=17;
    public static final int BOOLEAN=12;
    public static final int NAME=19;
    public static final int SWITCH=35;
    public static final int T__169=169;
    public static final int FeatureCall=61;
    public static final int T__122=122;
    public static final int T__121=121;
    public static final int T__124=124;
    public static final int FLOAT=4;
    public static final int T__123=123;
    public static final int OVERWRITE=87;
    public static final int T__120=120;
    public static final int NativeType=57;
    public static final int INT=8;
    public static final int ANNOTATIONBLOCK=51;
    public static final int RETURN=38;
    public static final int KEYVALLIST=78;
    public static final int FEATURECALL=64;
    public static final int CollectionType=45;
    public static final int T__119=119;
    public static final int ASSIGNMENT=26;
    public static final int T__118=118;
    public static final int T__115=115;
    public static final int WS=20;
    public static final int EOF=-1;
    public static final int T__114=114;
    public static final int T__117=117;
    public static final int T__116=116;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int EscapeSequence=13;
    public static final int EOLMODULE=62;
    public static final int DIGIT=5;
    public static final int EXECUTABLEANNOTATION=52;
    public static final int T__108=108;
    public static final int T__107=107;
    public static final int WHILE=34;
    public static final int T__109=109;
    public static final int GENERATE=83;
    public static final int T__104=104;
    public static final int POINT=9;
    public static final int T__103=103;
    public static final int T__106=106;
    public static final int DEFAULT=37;
    public static final int T__105=105;

    // delegates
    // delegators
    public EgxParser gEgx;


        public Egx_EgxParserRules(TokenStream input, EgxParser gEgx) {
            this(input, new RecognizerSharedState(), gEgx);
        }
        public Egx_EgxParserRules(TokenStream input, RecognizerSharedState state, EgxParser gEgx) {
            super(input, state);
            this.gEgx = gEgx;
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return EgxParser.tokenNames; }
    public String getGrammarFileName() { return "EgxParserRules.g"; }


    public static class egxModuleContent_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start egxModuleContent
    // EgxParserRules.g:51:1: egxModuleContent : ( generationRule | erlModuleContent );
    public final Egx_EgxParserRules.egxModuleContent_return egxModuleContent() throws RecognitionException {
        Egx_EgxParserRules.egxModuleContent_return retval = new Egx_EgxParserRules.egxModuleContent_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Egx_EgxParserRules.generationRule_return generationRule1 = null;

        Egx_ErlParserRules.erlModuleContent_return erlModuleContent2 = null;



        try {
            // EgxParserRules.g:52:2: ( generationRule | erlModuleContent )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==170) ) {
                alt1=1;
            }
            else if ( (LA1_0==Annotation||(LA1_0>=98 && LA1_0<=99)||LA1_0==104||(LA1_0>=166 && LA1_0<=167)) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // EgxParserRules.g:52:4: generationRule
                    {
                    root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

                    pushFollow(FOLLOW_generationRule_in_egxModuleContent60);
                    generationRule1=generationRule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, generationRule1.getTree());

                    }
                    break;
                case 2 :
                    // EgxParserRules.g:52:21: erlModuleContent
                    {
                    root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

                    pushFollow(FOLLOW_erlModuleContent_in_egxModuleContent64);
                    erlModuleContent2=gEgx.erlModuleContent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, erlModuleContent2.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end egxModuleContent

    public static class generationRule_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start generationRule
    // EgxParserRules.g:55:1: generationRule : r= 'rule' rule= NAME ( 'transform' formalParameter ( domain )? )? ob= '{' generationRuleConstructs cb= '}' ;
    public final Egx_EgxParserRules.generationRule_return generationRule() throws RecognitionException {
        Egx_EgxParserRules.generationRule_return retval = new Egx_EgxParserRules.generationRule_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token r=null;
        Token rule=null;
        Token ob=null;
        Token cb=null;
        Token string_literal3=null;
        Egx_EolParserRules.formalParameter_return formalParameter4 = null;

        Egx_EgxParserRules.domain_return domain5 = null;

        Egx_EgxParserRules.generationRuleConstructs_return generationRuleConstructs6 = null;


        org.eclipse.epsilon.common.parse.AST r_tree=null;
        org.eclipse.epsilon.common.parse.AST rule_tree=null;
        org.eclipse.epsilon.common.parse.AST ob_tree=null;
        org.eclipse.epsilon.common.parse.AST cb_tree=null;
        org.eclipse.epsilon.common.parse.AST string_literal3_tree=null;

        try {
            // EgxParserRules.g:60:2: (r= 'rule' rule= NAME ( 'transform' formalParameter ( domain )? )? ob= '{' generationRuleConstructs cb= '}' )
            // EgxParserRules.g:60:4: r= 'rule' rule= NAME ( 'transform' formalParameter ( domain )? )? ob= '{' generationRuleConstructs cb= '}'
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            r=(Token)match(input,170,FOLLOW_170_in_generationRule83); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            r_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(r);
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.becomeRoot(r_tree, root_0);
            }
            rule=(Token)match(input,NAME,FOLLOW_NAME_in_generationRule88); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            rule_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(rule);
            adaptor.addChild(root_0, rule_tree);
            }
            // EgxParserRules.g:60:24: ( 'transform' formalParameter ( domain )? )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==171) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // EgxParserRules.g:60:25: 'transform' formalParameter ( domain )?
                    {
                    string_literal3=(Token)match(input,171,FOLLOW_171_in_generationRule91); if (state.failed) return retval;
                    pushFollow(FOLLOW_formalParameter_in_generationRule94);
                    formalParameter4=gEgx.formalParameter();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameter4.getTree());
                    // EgxParserRules.g:60:54: ( domain )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==127) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // EgxParserRules.g:0:0: domain
                            {
                            pushFollow(FOLLOW_domain_in_generationRule96);
                            domain5=domain();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, domain5.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }

            ob=(Token)match(input,95,FOLLOW_95_in_generationRule104); if (state.failed) return retval;
            pushFollow(FOLLOW_generationRuleConstructs_in_generationRule107);
            generationRuleConstructs6=generationRuleConstructs();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, generationRuleConstructs6.getTree());
            cb=(Token)match(input,96,FOLLOW_96_in_generationRule111); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              r.setType(GENERATE);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

              		((org.eclipse.epsilon.common.parse.AST)retval.tree).getExtraTokens().add(ob);
              		((org.eclipse.epsilon.common.parse.AST)retval.tree).getExtraTokens().add(cb);
              	
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end generationRule

    public static class domain_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start domain
    // EgxParserRules.g:65:1: domain : (c= 'in' ) expressionOrStatementBlock ;
    public final Egx_EgxParserRules.domain_return domain() throws RecognitionException {
        Egx_EgxParserRules.domain_return retval = new Egx_EgxParserRules.domain_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token c=null;
        Egx_EolParserRules.expressionOrStatementBlock_return expressionOrStatementBlock7 = null;


        org.eclipse.epsilon.common.parse.AST c_tree=null;

        try {
            // EgxParserRules.g:65:8: ( (c= 'in' ) expressionOrStatementBlock )
            // EgxParserRules.g:66:2: (c= 'in' ) expressionOrStatementBlock
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            // EgxParserRules.g:66:2: (c= 'in' )
            // EgxParserRules.g:66:3: c= 'in'
            {
            c=(Token)match(input,127,FOLLOW_127_in_domain129); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            c_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(c);
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.becomeRoot(c_tree, root_0);
            }

            }

            pushFollow(FOLLOW_expressionOrStatementBlock_in_domain133);
            expressionOrStatementBlock7=gEgx.expressionOrStatementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionOrStatementBlock7.getTree());
            if ( state.backtracking==0 ) {
              c.setType(DOMAIN);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end domain

    public static class target_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start target
    // EgxParserRules.g:70:1: target : g= 'target' expressionOrStatementBlock ;
    public final Egx_EgxParserRules.target_return target() throws RecognitionException {
        Egx_EgxParserRules.target_return retval = new Egx_EgxParserRules.target_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token g=null;
        Egx_EolParserRules.expressionOrStatementBlock_return expressionOrStatementBlock8 = null;


        org.eclipse.epsilon.common.parse.AST g_tree=null;

        try {
            // EgxParserRules.g:71:2: (g= 'target' expressionOrStatementBlock )
            // EgxParserRules.g:71:4: g= 'target' expressionOrStatementBlock
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            g=(Token)match(input,172,FOLLOW_172_in_target150); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            g_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(g);
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.becomeRoot(g_tree, root_0);
            }
            pushFollow(FOLLOW_expressionOrStatementBlock_in_target153);
            expressionOrStatementBlock8=gEgx.expressionOrStatementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionOrStatementBlock8.getTree());
            if ( state.backtracking==0 ) {
              g.setType(TARGET);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end target

    public static class template_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start template
    // EgxParserRules.g:75:1: template : g= 'template' expressionOrStatementBlock ;
    public final Egx_EgxParserRules.template_return template() throws RecognitionException {
        Egx_EgxParserRules.template_return retval = new Egx_EgxParserRules.template_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token g=null;
        Egx_EolParserRules.expressionOrStatementBlock_return expressionOrStatementBlock9 = null;


        org.eclipse.epsilon.common.parse.AST g_tree=null;

        try {
            // EgxParserRules.g:76:2: (g= 'template' expressionOrStatementBlock )
            // EgxParserRules.g:76:4: g= 'template' expressionOrStatementBlock
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            g=(Token)match(input,173,FOLLOW_173_in_template169); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            g_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(g);
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.becomeRoot(g_tree, root_0);
            }
            pushFollow(FOLLOW_expressionOrStatementBlock_in_template172);
            expressionOrStatementBlock9=gEgx.expressionOrStatementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionOrStatementBlock9.getTree());
            if ( state.backtracking==0 ) {
              g.setType(TEMPLATE);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end template

    public static class parameters_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parameters
    // EgxParserRules.g:80:1: parameters : g= 'parameters' expressionOrStatementBlock ;
    public final Egx_EgxParserRules.parameters_return parameters() throws RecognitionException {
        Egx_EgxParserRules.parameters_return retval = new Egx_EgxParserRules.parameters_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token g=null;
        Egx_EolParserRules.expressionOrStatementBlock_return expressionOrStatementBlock10 = null;


        org.eclipse.epsilon.common.parse.AST g_tree=null;

        try {
            // EgxParserRules.g:81:2: (g= 'parameters' expressionOrStatementBlock )
            // EgxParserRules.g:81:4: g= 'parameters' expressionOrStatementBlock
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            g=(Token)match(input,174,FOLLOW_174_in_parameters188); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            g_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(g);
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.becomeRoot(g_tree, root_0);
            }
            pushFollow(FOLLOW_expressionOrStatementBlock_in_parameters191);
            expressionOrStatementBlock10=gEgx.expressionOrStatementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionOrStatementBlock10.getTree());
            if ( state.backtracking==0 ) {
              g.setType(PARAMETERS);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end parameters

    public static class overwrite_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start overwrite
    // EgxParserRules.g:85:1: overwrite : g= 'overwrite' expressionOrStatementBlock ;
    public final Egx_EgxParserRules.overwrite_return overwrite() throws RecognitionException {
        Egx_EgxParserRules.overwrite_return retval = new Egx_EgxParserRules.overwrite_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token g=null;
        Egx_EolParserRules.expressionOrStatementBlock_return expressionOrStatementBlock11 = null;


        org.eclipse.epsilon.common.parse.AST g_tree=null;

        try {
            // EgxParserRules.g:86:2: (g= 'overwrite' expressionOrStatementBlock )
            // EgxParserRules.g:86:4: g= 'overwrite' expressionOrStatementBlock
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            g=(Token)match(input,175,FOLLOW_175_in_overwrite207); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            g_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(g);
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.becomeRoot(g_tree, root_0);
            }
            pushFollow(FOLLOW_expressionOrStatementBlock_in_overwrite210);
            expressionOrStatementBlock11=gEgx.expressionOrStatementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionOrStatementBlock11.getTree());
            if ( state.backtracking==0 ) {
              g.setType(OVERWRITE);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end overwrite

    public static class merge_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start merge
    // EgxParserRules.g:90:1: merge : (g= 'merge' | 'protectRegions' ) expressionOrStatementBlock ;
    public final Egx_EgxParserRules.merge_return merge() throws RecognitionException {
        Egx_EgxParserRules.merge_return retval = new Egx_EgxParserRules.merge_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Token g=null;
        Token string_literal12=null;
        Egx_EolParserRules.expressionOrStatementBlock_return expressionOrStatementBlock13 = null;


        org.eclipse.epsilon.common.parse.AST g_tree=null;
        org.eclipse.epsilon.common.parse.AST string_literal12_tree=null;

        try {
            // EgxParserRules.g:91:2: ( (g= 'merge' | 'protectRegions' ) expressionOrStatementBlock )
            // EgxParserRules.g:91:4: (g= 'merge' | 'protectRegions' ) expressionOrStatementBlock
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            // EgxParserRules.g:91:4: (g= 'merge' | 'protectRegions' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==176) ) {
                alt4=1;
            }
            else if ( (LA4_0==177) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // EgxParserRules.g:91:5: g= 'merge'
                    {
                    g=(Token)match(input,176,FOLLOW_176_in_merge227); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    g_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(g);
                    adaptor.addChild(root_0, g_tree);
                    }

                    }
                    break;
                case 2 :
                    // EgxParserRules.g:91:15: 'protectRegions'
                    {
                    string_literal12=(Token)match(input,177,FOLLOW_177_in_merge229); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal12_tree = (org.eclipse.epsilon.common.parse.AST)adaptor.create(string_literal12);
                    adaptor.addChild(root_0, string_literal12_tree);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_expressionOrStatementBlock_in_merge233);
            expressionOrStatementBlock13=gEgx.expressionOrStatementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionOrStatementBlock13.getTree());
            if ( state.backtracking==0 ) {
              g.setType(MERGE);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end merge

    public static class generationRuleConstructs_return extends ParserRuleReturnScope {
        org.eclipse.epsilon.common.parse.AST tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start generationRuleConstructs
    // EgxParserRules.g:95:1: generationRuleConstructs : ( guard | pre | overwrite | merge | template | parameters | target | post )* ;
    public final Egx_EgxParserRules.generationRuleConstructs_return generationRuleConstructs() throws RecognitionException {
        Egx_EgxParserRules.generationRuleConstructs_return retval = new Egx_EgxParserRules.generationRuleConstructs_return();
        retval.start = input.LT(1);

        org.eclipse.epsilon.common.parse.AST root_0 = null;

        Egx_ErlParserRules.guard_return guard14 = null;

        Egx_ErlParserRules.pre_return pre15 = null;

        Egx_EgxParserRules.overwrite_return overwrite16 = null;

        Egx_EgxParserRules.merge_return merge17 = null;

        Egx_EgxParserRules.template_return template18 = null;

        Egx_EgxParserRules.parameters_return parameters19 = null;

        Egx_EgxParserRules.target_return target20 = null;

        Egx_ErlParserRules.post_return post21 = null;



        try {
            // EgxParserRules.g:96:2: ( ( guard | pre | overwrite | merge | template | parameters | target | post )* )
            // EgxParserRules.g:96:4: ( guard | pre | overwrite | merge | template | parameters | target | post )*
            {
            root_0 = (org.eclipse.epsilon.common.parse.AST)adaptor.nil();

            // EgxParserRules.g:96:4: ( guard | pre | overwrite | merge | template | parameters | target | post )*
            loop5:
            do {
                int alt5=9;
                switch ( input.LA(1) ) {
                case 168:
                    {
                    alt5=1;
                    }
                    break;
                case 166:
                    {
                    alt5=2;
                    }
                    break;
                case 175:
                    {
                    alt5=3;
                    }
                    break;
                case 176:
                case 177:
                    {
                    alt5=4;
                    }
                    break;
                case 173:
                    {
                    alt5=5;
                    }
                    break;
                case 174:
                    {
                    alt5=6;
                    }
                    break;
                case 172:
                    {
                    alt5=7;
                    }
                    break;
                case 167:
                    {
                    alt5=8;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // EgxParserRules.g:96:5: guard
            	    {
            	    pushFollow(FOLLOW_guard_in_generationRuleConstructs248);
            	    guard14=gEgx.guard();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, guard14.getTree());

            	    }
            	    break;
            	case 2 :
            	    // EgxParserRules.g:96:13: pre
            	    {
            	    pushFollow(FOLLOW_pre_in_generationRuleConstructs252);
            	    pre15=gEgx.pre();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, pre15.getTree());

            	    }
            	    break;
            	case 3 :
            	    // EgxParserRules.g:96:19: overwrite
            	    {
            	    pushFollow(FOLLOW_overwrite_in_generationRuleConstructs256);
            	    overwrite16=overwrite();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, overwrite16.getTree());

            	    }
            	    break;
            	case 4 :
            	    // EgxParserRules.g:96:31: merge
            	    {
            	    pushFollow(FOLLOW_merge_in_generationRuleConstructs260);
            	    merge17=merge();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, merge17.getTree());

            	    }
            	    break;
            	case 5 :
            	    // EgxParserRules.g:96:39: template
            	    {
            	    pushFollow(FOLLOW_template_in_generationRuleConstructs264);
            	    template18=template();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, template18.getTree());

            	    }
            	    break;
            	case 6 :
            	    // EgxParserRules.g:96:50: parameters
            	    {
            	    pushFollow(FOLLOW_parameters_in_generationRuleConstructs268);
            	    parameters19=parameters();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameters19.getTree());

            	    }
            	    break;
            	case 7 :
            	    // EgxParserRules.g:96:63: target
            	    {
            	    pushFollow(FOLLOW_target_in_generationRuleConstructs272);
            	    target20=target();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, target20.getTree());

            	    }
            	    break;
            	case 8 :
            	    // EgxParserRules.g:96:72: post
            	    {
            	    pushFollow(FOLLOW_post_in_generationRuleConstructs276);
            	    post21=gEgx.post();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, post21.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (org.eclipse.epsilon.common.parse.AST)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end generationRuleConstructs

    // Delegated rules


 

    public static final BitSet FOLLOW_generationRule_in_egxModuleContent60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_erlModuleContent_in_egxModuleContent64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_170_in_generationRule83 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_NAME_in_generationRule88 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_171_in_generationRule91 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_formalParameter_in_generationRule94 = new BitSet(new long[]{0x0000000000000000L,0x8000000080000000L});
    public static final BitSet FOLLOW_domain_in_generationRule96 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_95_in_generationRule104 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_generationRuleConstructs_in_generationRule107 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_96_in_generationRule111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_domain129 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_expressionOrStatementBlock_in_domain133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_172_in_target150 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_expressionOrStatementBlock_in_target153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_173_in_template169 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_expressionOrStatementBlock_in_template172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_174_in_parameters188 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_expressionOrStatementBlock_in_parameters191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_175_in_overwrite207 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_expressionOrStatementBlock_in_overwrite210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_176_in_merge227 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_177_in_merge229 = new BitSet(new long[]{0x0000000000000000L,0x0000004080000000L});
    public static final BitSet FOLLOW_expressionOrStatementBlock_in_merge233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_guard_in_generationRuleConstructs248 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_pre_in_generationRuleConstructs252 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_overwrite_in_generationRuleConstructs256 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_merge_in_generationRuleConstructs260 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_template_in_generationRuleConstructs264 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_parameters_in_generationRuleConstructs268 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_target_in_generationRuleConstructs272 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});
    public static final BitSet FOLLOW_post_in_generationRuleConstructs276 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0003F1C000000000L});

}
