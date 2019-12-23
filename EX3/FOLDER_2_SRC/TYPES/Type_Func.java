package TYPES;

public class Type_Func extends Type {
    public Type returnType;
    public Type_List params;

    public Type_Func(Type returnType, String name, Type_List params) {
        this.name = name;
        this.returnType = returnType;
        this.params = params;
    }

    public Type GetReturnType() {
        return this.returnType;
    }

    public String GetName() {
        return this.name;
    }

    public Type_List GetParams() {
        return this.params;
    }

    public boolean IsValidTypeList(Type_List other_params) {

        Type_List selfTail = this.params;
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
                    CompatibleTypes(((Type_Array) selfType).elementType, ((Type_Array) otherType).elementType);
        }

        //classes
        if (selfType instanceof Type_Class) {
            return otherType instanceof Type_Class &&
                    ((Type_Class) otherType).isInheritsFrom(selfType.name);
        }


        return true;
    }


}
