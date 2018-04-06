package kr.co.bit.memberdao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.membervo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MemberDAO {

    public boolean insert(MemberVO vo) {
        boolean flag = false;
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        String sql = "insert into MEMBER_TBL values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, vo.getId());
            statement.setString(2, vo.getPw());
            statement.setString(3, vo.getName());
            statement.setString(4, vo.getEmail());
            statement.setString(5, vo.getZipcode());
            statement.setString(6, vo.getAddr1());
            statement.setString(7, vo.getAddr2());
            statement.setString(8, vo.getTool());
            String[] temp = vo.getLangs();
            StringBuffer stringBuffer = new StringBuffer(temp[0]);
            for (int i=1; i<temp.length;i++) {
                    stringBuffer.append("-" + temp[i]);
            }
            statement.setString(9, String.valueOf(stringBuffer));
            statement.setString(10, vo.getProject());
            int affectCount = statement.executeUpdate(); //영향을 받은 횟수
            if (affectCount>0) {
                flag = true;
                System.out.println("삽입완료");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, statement,null); //무조건 실행
        }
        return flag;
    }

    public ArrayList<MemberVO> select1(String id) { //리턴타입 MemberVO으로 해보기
        ArrayList<MemberVO> list = null;
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        Statement statement = null;

        try {

            statement = connection.createStatement();
            String sql = "select * from member_tbl where user_id='"+id+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            MemberVO vo = null;
            while (resultSet.next()) {
                vo = new MemberVO();
                vo.setId(resultSet.getString(1));
                vo.setPw(resultSet.getString(2));
                vo.setName(resultSet.getString(3));
                vo.setEmail(resultSet.getString(4));
                vo.setZipcode(resultSet.getString(5));
                vo.setAddr1(resultSet.getString(6));
                vo.setAddr2(resultSet.getString(7));
                vo.setTool(resultSet.getString(8));
                String temp = resultSet.getString(9);
                String[] langs = temp.split("-");
                String[] vals = {"","","",""};
                for ( String index :langs) {
                    int idx = Integer.parseInt(index);
                    vals[idx] =index;
                }
                vo.setLangs(vals);
                vo.setProject(resultSet.getString(10));
                list.add(vo);
            }
            connectionManager.connectClose(connection,statement,resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<MemberVO> select() {
        ArrayList<MemberVO> list = null;

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from departments";
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<MemberVO>();
            MemberVO vo = null;
            while (resultSet.next()) {
              vo = new MemberVO();
              vo.setId(resultSet.getString(1));
                vo.setPw(resultSet.getString(4));
                vo.setName(resultSet.getString(2));
                vo.setEmail(resultSet.getString(3));
                vo.setZipcode(resultSet.getString(5));
                vo.setAddr1(resultSet.getString(6));
                vo.setAddr2(resultSet.getString(7));
                vo.setTool(resultSet.getString(8));

                String temp = resultSet.getString(9);
                String[] langs = temp.split("-");
                String[] vals = {"","","",""};
                for ( String index :langs) {
                    int idx = Integer.parseInt(index);
                    vals[idx] =index;
                }
                vo.setLangs(vals);
                vo.setProject(resultSet.getString(10));
                list.add(vo);
            }
            connectionManager.connectClose(connection,statement,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
