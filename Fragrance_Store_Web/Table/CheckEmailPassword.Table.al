table 50103 "Check Email Password"
{
    Caption = 'Check Email Password';
    DataClassification = ToBeClassified;

    fields
    {
        field(1; "Entry No."; Integer)
        {
            Caption = 'Entry No.';
            DataClassification = CustomerContent;
            AutoIncrement = true;
        }

        field(10; Email; Text[100])
        {
            Caption = 'Email';
            DataClassification = CustomerContent;
        }

        field(20; Password; Text[100])
        {
            Caption = 'Password';
            DataClassification = CustomerContent;
        }
    }

    keys
    {
        key(PK; "Entry No.")
        {
            Clustered = true;
        }
    }

    trigger OnInsert()
    var
        FragranceManagement: Codeunit "Fragrance Management";
        SingleInstance: Codeunit "Single Instance";
    begin
        SingleInstance.SetSuccessfullyAuthenticated(FragranceManagement.CheckUserPassword(Email, Password));
        Password := '';
    end;
}