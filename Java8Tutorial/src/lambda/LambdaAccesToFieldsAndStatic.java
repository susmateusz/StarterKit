package lambda;

public class LambdaAccesToFieldsAndStatic {

	private static int outerStaticNum;
	private int outerNum;
	
	public String testScopes() {
		Converter<Integer,String> stringConverter1 = (from) -> {
			outerNum = 3;
			return String.valueOf(this.outerNum*from);
		};
		Converter<Integer,String> stringConverter2 = (from) -> {
			outerStaticNum = 2;
			return String.valueOf(LambdaAccesToFieldsAndStatic.outerStaticNum*from);
		};
		return ""+stringConverter1.convert(1)+stringConverter2.convert(10);
		
	}
}
