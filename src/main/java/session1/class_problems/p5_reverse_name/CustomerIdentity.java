package session1.class_problems.p5_reverse_name;

public class CustomerIdentity {
    public String reverseCustomerName(String customerName) {
        if (customerName == null) return null;
        char[] chars = customerName.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
    
    public void test(String name) {
        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reverseCustomerName(name));
    }
}
