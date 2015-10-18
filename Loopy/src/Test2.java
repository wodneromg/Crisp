
import java.util.*;
public class Test2 
{
	public static void main(String[] args)
	{
		String[] a={"零","壹","贰","叁","肆","伍","陆","柒","捌","玖","拾","佰"};
		Scanner input = new Scanner(System.in);
		System.out.println("请输入金额:");
		String n1 = input.next();
		if(n1.indexOf('.')==-1)//判断数据是否整数
		{
			int x = Integer.parseInt(n1);
			if(x/100!=0)//数据是否是百位数
			{
				int q = x/100;
				int p = (x%100)/10;
				int w = (x%100)%10;
				if(p!=0)
				{
					if(x/10==0)//十位数不是0
					{
						String sn1 = a[q];
						String sn2 = a[p];
						
						
						
					}
				}
			}
			
		}
		
	}

}
