package TYPES;

public class TYPE_CLASS extends TYPE {
    public TYPE_CLASS father;          //should be null if is type
    public TYPE_LIST data_members;     // contains both variables and methods

    public TYPE_CLASS(TYPE_CLASS father, String name, TYPE_LIST data_members) {
        this.name = name;
        this.father = father;
        this.data_members = data_members;
    }

    public boolean isInheritsFrom(TYPE_CLASS cls) {

        if (this.name.equals(cls.name)) {
            return true;
        }

        return this.father != null && this.father.isInheritsFrom(cls);
    }


    public TYPE getVarField(String varFieldName)
    {
        for (TYPE_LIST it = data_members; it != null; it = it.next)
        {
            if (it.head instanceof TYPE_CLASS_VAR_DEC)
            {
                TYPE_CLASS_VAR_DEC varFieldType = (TYPE_CLASS_VAR_DEC)it.head;
                if (varFieldName.equals(varFieldType.name)) { return varFieldType.type; }
            }
        }
        if (father != null) { return father.getVarField(varFieldName); }
        return null;
    }

    public TYPE_FUNCTION getFuncField(String funcFieldName)
    {
        for (TYPE_LIST it = data_members; it != null; it = it.next)
        {
            if (it.head instanceof TYPE_FUNCTION)
            {
                TYPE_FUNCTION func = (TYPE_FUNCTION)it.head;
                if (funcFieldName.equals(func.name)) { return func; }
            }
        }
        if (father != null) { return father.getFuncField(funcFieldName); }
        return null;
    }



}
