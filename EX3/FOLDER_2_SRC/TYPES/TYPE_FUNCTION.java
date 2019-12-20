package TYPES;

public class TYPE_FUNCTION extends TYPE {
    public TYPE returnType;
    public TYPE_LIST params;

    public TYPE_FUNCTION(TYPE returnType, String name, TYPE_LIST params) {
        this.name = name;
        this.returnType = returnType;
        this.params = params;
    }

    public TYPE GetReturnType() {
        return this.returnType;
    }

    public String GetName() {
        return this.name;
    }

    public TYPE_LIST GetParams() {
        return this.params;
    }

    public boolean IsValidTypeList(TYPE_LIST other_params) {

        TYPE_LIST selfTail = this.params;
        TYPE_LIST otherTail = other_params;
        while (selfTail != null && otherTail != null) {

            if (!CompetableTypes(selfTail.head, otherTail.head)) {
                return false;
            }


            selfTail = selfTail.next;
            otherTail = otherTail.next;
        }

        // return true if both lists ended
        return (otherTail == null) && (selfTail == null);

    }

    private boolean CompetableTypes(TYPE selfType, TYPE otherType) {

        if (otherType instanceof TYPE_NIL) {
            return selfType instanceof TYPE_CLASS || selfType instanceof TYPE_ARRAY;
        }

        /*we are not null*/

        // primitives
        if (selfType instanceof TYPE_INT) {
            return otherType instanceof TYPE_INT;
        }

        if (selfType instanceof TYPE_STRING) {
            return otherType instanceof TYPE_STRING;
        }

        //complex types

        //array
        if (selfType instanceof TYPE_ARRAY) {
            return otherType instanceof TYPE_ARRAY &&
                    CompetableTypes(((TYPE_ARRAY) selfType).elementType, ((TYPE_ARRAY) otherType).elementType);
        }

        //classes
        if (selfType instanceof TYPE_CLASS) {
            return otherType instanceof TYPE_CLASS &&
                    ((TYPE_CLASS) otherType).isInheritsFrom((TYPE_CLASS) selfType);
        }


        return true;
    }


}
