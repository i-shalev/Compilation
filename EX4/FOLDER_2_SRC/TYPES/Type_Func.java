package TYPES;
import AST.AST_Params_List;

import java.util.*;

public class Type_Func extends Type {
    public Type returnType;
    public Type_List paramsType;
    public AST_Params_List params;
    public String fullName;
    public List<Symbol> params2 = new ArrayList<>();
    public List<Symbol> locals = new ArrayList<>();
    public int currMaxLocals =0;

    public Type_Func(Type returnType, String name, Type_List paramsType, AST_Params_List params) {
        this.name = name;
        this.returnType = returnType;
        this.paramsType = paramsType;
        this.params = params;
        this.fullName=name;

        Type_List tmpType = paramsType;
        for(AST_Params_List it=params; it!=null; it=it.nextParam){
            params2.add(new Symbol(it.paramName, tmpType));
            tmpType = tmpType.next;
        }
    }

    public boolean IsValidTypeList(Type_List other_params) {

        Type_List selfTail = this.paramsType;
        Type_List otherTail = other_params;
        while (selfTail != null && otherTail != null) {

            if (!CompatibleTypes(selfTail.head, otherTail.head)) {
                return false;
            }


            selfTail = selfTail.next;
            otherTail = otherTail.next;
        }

        // return true if both lists ended
        return (otherTail == null) && (selfTail == null);

    }

    private boolean CompatibleTypes(Type selfType, Type otherType) {

        if (otherType instanceof Type_Nil) {
            return selfType instanceof Type_Class || selfType instanceof Type_Array;
        }

        /*we are not null*/

        // primitives
        if (selfType instanceof Type_Int) {
            return otherType instanceof Type_Int;
        }

        if (selfType instanceof Type_String) {
            return otherType instanceof Type_String;
        }

        //complex types

        //array
        if (selfType instanceof Type_Array) {
            return otherType instanceof Type_Array &&
                    selfType == otherType;
//                    CompatibleTypes(((Type_Array) selfType).elementType, ((Type_Array) otherType).elementType);
        }

        //classes
        if (selfType instanceof Type_Class) {
            return otherType instanceof Type_Class &&
                    ((Type_Class) otherType).isInheritsFrom(selfType.name);
        }


        return true;
    }

    public boolean isOverride(Type_Func fatherFunc) {

        if (!returnType.name.equals(fatherFunc.returnType.name))
            return false;

        Type_List params = this.paramsType, otherParams = fatherFunc.paramsType;

        // TODO: finish this
        while (params != null && otherParams != null){
            // each parameter has to be an object with the same type
            if (!(params.head instanceof Type_Object && otherParams.head instanceof Type_Object))
                return false;
            if (!(params.head.equals(otherParams.head)))
                return false;
            params = params.next;
            otherParams = otherParams.next;
        }

        return params == null && otherParams == null; // we reached the end in both
    }
}
