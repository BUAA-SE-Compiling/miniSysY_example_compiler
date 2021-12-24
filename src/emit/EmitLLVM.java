package emit;

import ir.Module;
import ir.values.instructions.BinaryInst;

import java.io.FileWriter;
import java.util.logging.Logger;

import ir.*;


public class EmitLLVM {

    public EmitLLVM(String outputName) {
        this.outputName = outputName;
        sb = new StringBuilder();
    }

    public EmitLLVM() {
        sb = new StringBuilder();
    }

    String outputName = "out.ll";
    StringBuilder sb;
    private int vnc = 0;//value name counter


    private String newName() {
        var v = String.valueOf(vnc);
        vnc++;
        return v;
    }

    private void nameVariable(Module m) {
        m.globalVariables.forEach(gv -> {
            if (!gv.name.startsWith("@")) {
                gv.name = "@" + gv.name;
            }
        });
        m.functions.forEach(
                f -> {
                    vnc = 0;
                    var func = f.getVal();
                    if (!func.isBuiltin()) {
                        func.getParamList().forEach(arg -> {
                            arg.name = "%" + newName();
                        });
                        func.list.forEach(bbInode -> {
                            // if (!bbInode.equals(func.getList_().getEntry())) {
                            bbInode.getVal().name = newName();
                            //}
                            bbInode.getVal().list.forEach(
                                    instNode -> {
                                        if (instNode.getVal().needName) {
                                            instNode.getVal().name = "%" + newName();
                                        }
                                    }
                            );
                        });
                    } else {
                        sb.append("declare ")
                                .append(func)
                                .append("\n");

                    }
                }
        );
    }

    public void run(Module m) {
        nameVariable(m);
        m.globalVariables.forEach(gb -> {
            sb.append(gb).append("\n");
        });
        m.functions.forEach(func -> {
            var val = func.getVal();
            if (!val.isBuiltin()) {
                sb.append("define dso_local ")
                        .append(val)
                        .append("{");
                sb.append("\n");

                val.list.forEach(
                        bbNode -> {
                            var bbval = bbNode.getVal();
                            if (!val.list.getEntry().equals(bbNode)) {
                                sb.append(bbval.name);
                                sb.append(":");
                                sb.append("\n");
                            }
                            bbval.list.forEach(
                                    instNode -> {
                                        var instVal = instNode.getVal();
                                        sb.append(instVal.toString());
                                        sb.append("\n");

                                    }
                            );
                        }
                );
                sb.append("}\n");
            } else {

            }
        });
        try {
            FileWriter fw = new FileWriter(outputName);
//      System.out.println(sb);
            fw.append(sb);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
