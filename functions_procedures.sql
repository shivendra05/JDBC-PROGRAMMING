
create or replace procedure selectAllDataWithCursor(acc number, data out SYS_REFCURSOR) is
  				begin
				open data for select * from bank_account where accno<acc;
 				end;
 				/
        
        
create or replace procedure selectAllDataWithCursor(acc number, data out SYS_REFCURSOR) is
  				begin
				open data for select * from bank_account where accno<acc;
 				end;
 				/
        
 create or replace procedure selectAmountWithInOutCursor(acc number, data out SYS_REFCURSOR) is
  				begin
				open data for select amount from bank_account where accno<acc;
 				end;
 				/
        
create or replace procedure salary_selection(acc number, salary out number) is
  				begin
				select amount into salary from bank_account where accno=acc;
 				end;
 				/
        
create or replace procedure amount_update(acc number) is
  				begin
				update bank_account set amount=amount+35 where accno=acc;
 				end;
 				/
        
create or replace procedure AccountAmount_update(dno number) is
  				begin
				update bank_account set amount=amount+50 where amount<10000;
 				end;
 				/
        
create or replace function fsalary_selection return number is
			 	salary number;
  				begin
				select amount into salary from bank_account where accno=1001;
        return salary;
 				end;
 				/        
        
create or replace function fsalaryAcc_selection(acc number) return number is
			 	salary number;
  				begin
				select amount into salary from bank_account where accno=acc;
        		return salary;
 				end;
 				/    
        
create or replace function fselectAllDataWithCursor(acc number) return SYS_REFCURSOR is
			 	data SYS_REFCURSOR;
  				begin
				open data for select * from bank_account where accno<acc;
				return data;
 				end;
 				/        
        
create or replace function fUpdateSelectAllDataWithCursor(acc number) return SYS_REFCURSOR is
			 	data SYS_REFCURSOR;
  				begin
  				update bank_account set amount=amount+11 where accno<acc;
				open data for select * from bank_account where accno<acc;
				return data;
 				end;
 				/ 