package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList 관리 명령어 사용 방법>");
        System.out.println("Multi-item 기능을 사용하시고 싶으시면, 명령어 뒤에 \"_m\" 을 붙이십시오.");
        System.out.println("1. 항목 추가 ( add )");
        System.out.println("2. 항목 삭제 ( del )");
        System.out.println("3. 항목 수정 ( edit )");
        System.out.println("4. 완료 관리 ( complete )");
        System.out.println("5. 전체 목록 ( ls )");
        System.out.println("6. 카테고리 목록 ( ls_cate )");
        System.out.println("7. 제목순 정렬 ( ls_name_asc )");
        System.out.println("8. 제목역순 정렬 ( ls_name_desc )");
        System.out.println("9. 날짜순 정렬 ( ls_date )");
        System.out.println("10. 최신순 정렬 ( ls_date_desc )");
        System.out.println("11. 키워드 검색 ( find <키워드> )");
        System.out.println("12. 카테고리 검색 ( find_cate <키워드> )");
        System.out.println("13. 종료 ( exit )");
    }
    public static void prompt() {
    	System.out.print("\n명령어 입력 >>");
    }
}