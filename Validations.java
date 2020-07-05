import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validations 
{
	public boolean isPositiveInteger(String num)
	{
		return num.chars().allMatch(Character::isDigit);
	}
	
	public boolean isValidName(String name)
	{
		if(name.length > 30) return false;
		else if(isStringHasNumsOrSpecialChars(name)) return false;
		else return true;
	}

	public boolean isStringHasNumsOrSpecialChars(String name)
	{
		Pattern pattern = Pattern.compile("[a-zA-Z]*");
		Matcher matcher = pattern.matcher(name);
		if(matcher.matches()) return false;
		else return true;
	}
}