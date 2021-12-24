declare void @memset(i32*  ,i32 ,i32 )
declare i32 @getint()
declare i32 @getarray(i32*  )
declare i32 @getch()
declare void @putint(i32 )
declare void @putch(i32 )
declare void @putarray(i32 ,i32*  )
@dp = dso_local global [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]] zeroinitializer 
@list = dso_local global [200 x i32] zeroinitializer 
@cns = dso_local global [20 x i32] zeroinitializer 
define dso_local i32 @equal(i32 %0,i32 %1){
%3 = alloca i32
%4 = alloca i32
store i32 %0, i32*  %4
store i32 %1, i32*  %3
%5 = load i32,i32*  %4
%6 = load i32,i32*  %3
%7 = icmp eq i32 %5,%6
br i1 %7,label  %10,label  %9 

8:
ret i32 1
9:
ret i32 0
10:
br label  %8

}
define dso_local i32 @dfs(i32 %0,i32 %1,i32 %2,i32 %3,i32 %4,i32 %5){
%7 = alloca i32
%8 = alloca i32
%9 = alloca i32
%10 = alloca i32
%11 = alloca i32
%12 = alloca i32
%13 = alloca i32
store i32 %0, i32*  %13
store i32 %1, i32*  %12
store i32 %2, i32*  %11
store i32 %3, i32*  %10
store i32 %4, i32*  %9
store i32 %5, i32*  %8
%14 = load i32,i32*  %13
%15= getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]*  @dp , i32 0, i32 %14
%16 = load i32,i32*  %12
%17= getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]*  %15 , i32 0, i32 %16
%18 = load i32,i32*  %11
%19= getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]*  %17 , i32 0, i32 %18
%20 = load i32,i32*  %10
%21= getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]*  %19 , i32 0, i32 %20
%22 = load i32,i32*  %9
%23= getelementptr [18 x [7 x i32]],[18 x [7 x i32]]*  %21 , i32 0, i32 %22
%24 = load i32,i32*  %8
%25= getelementptr [7 x i32],[7 x i32]*  %23 , i32 0, i32 %24
%26 = load i32,i32*  %25
%27 = sub  i32 0,1
%28 = icmp ne  i32 %26,%27
br i1 %28,label  %54,label  %43 

29:
%30 = load i32,i32*  %13
%31= getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]*  @dp , i32 0, i32 %30
%32 = load i32,i32*  %12
%33= getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]*  %31 , i32 0, i32 %32
%34 = load i32,i32*  %11
%35= getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]*  %33 , i32 0, i32 %34
%36 = load i32,i32*  %10
%37= getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]*  %35 , i32 0, i32 %36
%38 = load i32,i32*  %9
%39= getelementptr [18 x [7 x i32]],[18 x [7 x i32]]*  %37 , i32 0, i32 %38
%40 = load i32,i32*  %8
%41= getelementptr [7 x i32],[7 x i32]*  %39 , i32 0, i32 %40
%42 = load i32,i32*  %41
ret i32 %42
43:
%44 = load i32,i32*  %13
%45 = load i32,i32*  %12
%46 = add  i32 %44,%45
%47 = load i32,i32*  %11
%48 = add  i32 %46,%47
%49 = load i32,i32*  %10
%50 = add  i32 %48,%49
%51 = load i32,i32*  %9
%52 = add  i32 %50,%51
%53 = icmp eq i32 %52,0
br i1 %53,label  %59,label  %56 

54:
br label  %29

55:
ret i32 1
56:
store i32 0, i32*  %7
%57 = load i32,i32*  %13
%58 = icmp ne  i32 %57,0
br i1 %58,label  %81,label  %78 

59:
br label  %55

60:
%61 = load i32,i32*  %7
%62 = load i32,i32*  %13
%63 = load i32,i32*  %8
%64 = call i32 @equal(i32 %63,i32 2)
%65 = sub  i32 %62,%64
%66 = load i32,i32*  %13
%67 = sub  i32 %66,1
%68 = load i32,i32*  %12
%69 = load i32,i32*  %11
%70 = load i32,i32*  %10
%71 = load i32,i32*  %9
%72 = call i32 @dfs(i32 %67,i32 %68,i32 %69,i32 %70,i32 %71,i32 1)
%73 = mul  i32 %65,%72
%74 = add  i32 %61,%73
%75 = sdiv  i32 %74,1000000007
%76 = mul  i32 %75,1000000007
%77 = sub  i32 %74,%76
store i32 %77, i32*  %7
br label  %78

78:
%79 = load i32,i32*  %12
%80 = icmp ne  i32 %79,0
br i1 %80,label  %104,label  %101 

81:
br label  %60

82:
%83 = load i32,i32*  %7
%84 = load i32,i32*  %12
%85 = load i32,i32*  %8
%86 = call i32 @equal(i32 %85,i32 3)
%87 = sub  i32 %84,%86
%88 = load i32,i32*  %13
%89 = add  i32 %88,1
%90 = load i32,i32*  %12
%91 = sub  i32 %90,1
%92 = load i32,i32*  %11
%93 = load i32,i32*  %10
%94 = load i32,i32*  %9
%95 = call i32 @dfs(i32 %89,i32 %91,i32 %92,i32 %93,i32 %94,i32 2)
%96 = mul  i32 %87,%95
%97 = add  i32 %83,%96
%98 = sdiv  i32 %97,1000000007
%99 = mul  i32 %98,1000000007
%100 = sub  i32 %97,%99
store i32 %100, i32*  %7
br label  %101

101:
%102 = load i32,i32*  %11
%103 = icmp ne  i32 %102,0
br i1 %103,label  %127,label  %124 

104:
br label  %82

105:
%106 = load i32,i32*  %7
%107 = load i32,i32*  %11
%108 = load i32,i32*  %8
%109 = call i32 @equal(i32 %108,i32 4)
%110 = sub  i32 %107,%109
%111 = load i32,i32*  %13
%112 = load i32,i32*  %12
%113 = add  i32 %112,1
%114 = load i32,i32*  %11
%115 = sub  i32 %114,1
%116 = load i32,i32*  %10
%117 = load i32,i32*  %9
%118 = call i32 @dfs(i32 %111,i32 %113,i32 %115,i32 %116,i32 %117,i32 3)
%119 = mul  i32 %110,%118
%120 = add  i32 %106,%119
%121 = sdiv  i32 %120,1000000007
%122 = mul  i32 %121,1000000007
%123 = sub  i32 %120,%122
store i32 %123, i32*  %7
br label  %124

124:
%125 = load i32,i32*  %10
%126 = icmp ne  i32 %125,0
br i1 %126,label  %150,label  %147 

127:
br label  %105

128:
%129 = load i32,i32*  %7
%130 = load i32,i32*  %10
%131 = load i32,i32*  %8
%132 = call i32 @equal(i32 %131,i32 5)
%133 = sub  i32 %130,%132
%134 = load i32,i32*  %13
%135 = load i32,i32*  %12
%136 = load i32,i32*  %11
%137 = add  i32 %136,1
%138 = load i32,i32*  %10
%139 = sub  i32 %138,1
%140 = load i32,i32*  %9
%141 = call i32 @dfs(i32 %134,i32 %135,i32 %137,i32 %139,i32 %140,i32 4)
%142 = mul  i32 %133,%141
%143 = add  i32 %129,%142
%144 = sdiv  i32 %143,1000000007
%145 = mul  i32 %144,1000000007
%146 = sub  i32 %143,%145
store i32 %146, i32*  %7
br label  %147

147:
%148 = load i32,i32*  %9
%149 = icmp ne  i32 %148,0
br i1 %149,label  %197,label  %167 

150:
br label  %128

151:
%152 = load i32,i32*  %7
%153 = load i32,i32*  %9
%154 = load i32,i32*  %13
%155 = load i32,i32*  %12
%156 = load i32,i32*  %11
%157 = load i32,i32*  %10
%158 = add  i32 %157,1
%159 = load i32,i32*  %9
%160 = sub  i32 %159,1
%161 = call i32 @dfs(i32 %154,i32 %155,i32 %156,i32 %158,i32 %160,i32 5)
%162 = mul  i32 %153,%161
%163 = add  i32 %152,%162
%164 = sdiv  i32 %163,1000000007
%165 = mul  i32 %164,1000000007
%166 = sub  i32 %163,%165
store i32 %166, i32*  %7
br label  %167

167:
%168 = load i32,i32*  %13
%169= getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]*  @dp , i32 0, i32 %168
%170 = load i32,i32*  %12
%171= getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]*  %169 , i32 0, i32 %170
%172 = load i32,i32*  %11
%173= getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]*  %171 , i32 0, i32 %172
%174 = load i32,i32*  %10
%175= getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]*  %173 , i32 0, i32 %174
%176 = load i32,i32*  %9
%177= getelementptr [18 x [7 x i32]],[18 x [7 x i32]]*  %175 , i32 0, i32 %176
%178 = load i32,i32*  %8
%179= getelementptr [7 x i32],[7 x i32]*  %177 , i32 0, i32 %178
%180 = load i32,i32*  %7
%181 = sdiv  i32 %180,1000000007
%182 = mul  i32 %181,1000000007
%183 = sub  i32 %180,%182
store i32 %183, i32*  %179
%184 = load i32,i32*  %13
%185= getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]*  @dp , i32 0, i32 %184
%186 = load i32,i32*  %12
%187= getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]*  %185 , i32 0, i32 %186
%188 = load i32,i32*  %11
%189= getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]*  %187 , i32 0, i32 %188
%190 = load i32,i32*  %10
%191= getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]*  %189 , i32 0, i32 %190
%192 = load i32,i32*  %9
%193= getelementptr [18 x [7 x i32]],[18 x [7 x i32]]*  %191 , i32 0, i32 %192
%194 = load i32,i32*  %8
%195= getelementptr [7 x i32],[7 x i32]*  %193 , i32 0, i32 %194
%196 = load i32,i32*  %195
ret i32 %196
197:
br label  %151

}
define dso_local i32 @main(){
%1 = alloca i32
%2 = alloca i32
%3 = alloca i32
%4 = alloca i32
%5 = alloca i32
%6 = alloca i32
%7 = alloca i32
%8 = alloca i32
%9 = call i32 @getint()
store i32 %9, i32*  %8
store i32 0, i32*  %7
br label  %10

10:
%11 = load i32,i32*  %7
%12 = icmp slt i32 %11,18
br i1 %12,label  %15,label  %14 

13:
store i32 0, i32*  %6
br label  %16

14:
store i32 0, i32*  %7
br label  %71

15:
br label  %13

16:
%17 = load i32,i32*  %6
%18 = icmp slt i32 %17,18
br i1 %18,label  %23,label  %20 

19:
store i32 0, i32*  %5
br label  %24

20:
%21 = load i32,i32*  %7
%22 = add  i32 %21,1
store i32 %22, i32*  %7
br label  %10

23:
br label  %19

24:
%25 = load i32,i32*  %5
%26 = icmp slt i32 %25,18
br i1 %26,label  %31,label  %28 

27:
store i32 0, i32*  %4
br label  %32

28:
%29 = load i32,i32*  %6
%30 = add  i32 %29,1
store i32 %30, i32*  %6
br label  %16

31:
br label  %27

32:
%33 = load i32,i32*  %4
%34 = icmp slt i32 %33,18
br i1 %34,label  %39,label  %36 

35:
store i32 0, i32*  %3
br label  %40

36:
%37 = load i32,i32*  %5
%38 = add  i32 %37,1
store i32 %38, i32*  %5
br label  %24

39:
br label  %35

40:
%41 = load i32,i32*  %3
%42 = icmp slt i32 %41,18
br i1 %42,label  %47,label  %44 

43:
store i32 0, i32*  %2
br label  %48

44:
%45 = load i32,i32*  %4
%46 = add  i32 %45,1
store i32 %46, i32*  %4
br label  %32

47:
br label  %43

48:
%49 = load i32,i32*  %2
%50 = icmp slt i32 %49,7
br i1 %50,label  %70,label  %67 

51:
%52 = load i32,i32*  %7
%53= getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]*  @dp , i32 0, i32 %52
%54 = load i32,i32*  %6
%55= getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]*  %53 , i32 0, i32 %54
%56 = load i32,i32*  %5
%57= getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]*  %55 , i32 0, i32 %56
%58 = load i32,i32*  %4
%59= getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]*  %57 , i32 0, i32 %58
%60 = load i32,i32*  %3
%61= getelementptr [18 x [7 x i32]],[18 x [7 x i32]]*  %59 , i32 0, i32 %60
%62 = load i32,i32*  %2
%63= getelementptr [7 x i32],[7 x i32]*  %61 , i32 0, i32 %62
%64 = sub  i32 0,1
store i32 %64, i32*  %63
%65 = load i32,i32*  %2
%66 = add  i32 %65,1
store i32 %66, i32*  %2
br label  %48

67:
%68 = load i32,i32*  %3
%69 = add  i32 %68,1
store i32 %69, i32*  %3
br label  %40

70:
br label  %51

71:
%72 = load i32,i32*  %7
%73 = load i32,i32*  %8
%74 = icmp slt i32 %72,%73
br i1 %74,label  %105,label  %91 

75:
%76 = load i32,i32*  %7
%77= getelementptr [200 x i32],[200 x i32]*  @list , i32 0, i32 %76
%78 = call i32 @getint()
store i32 %78, i32*  %77
%79 = load i32,i32*  %7
%80= getelementptr [200 x i32],[200 x i32]*  @list , i32 0, i32 %79
%81 = load i32,i32*  %80
%82= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 %81
%83 = load i32,i32*  %7
%84= getelementptr [200 x i32],[200 x i32]*  @list , i32 0, i32 %83
%85 = load i32,i32*  %84
%86= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 %85
%87 = load i32,i32*  %86
%88 = add  i32 %87,1
store i32 %88, i32*  %82
%89 = load i32,i32*  %7
%90 = add  i32 %89,1
store i32 %90, i32*  %7
br label  %71

91:
%92= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 1
%93 = load i32,i32*  %92
%94= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 2
%95 = load i32,i32*  %94
%96= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 3
%97 = load i32,i32*  %96
%98= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 4
%99 = load i32,i32*  %98
%100= getelementptr [20 x i32],[20 x i32]*  @cns , i32 0, i32 5
%101 = load i32,i32*  %100
%102 = call i32 @dfs(i32 %93,i32 %95,i32 %97,i32 %99,i32 %101,i32 0)
store i32 %102, i32*  %1
%103 = load i32,i32*  %1
call void @putint(i32 %103)
%104 = load i32,i32*  %1
ret i32 %104
105:
br label  %75

}
