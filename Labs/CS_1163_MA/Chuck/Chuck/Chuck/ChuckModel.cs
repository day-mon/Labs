using System;
using System.Collections.Generic;
using System.Text;

namespace Chuck
{
    public class ChuckModel
    {
        public string id { get; set; }
        public string ChuckQuote { get; set; }
        public DateTimeOffset QuoteDate { get; set; }
        public string userID { get; set; }
        public string EnteredBy { get; set; }

        public string DateAndEnterBy { get; set; }
        private string DateAndEnterByProp
        {
            get { return DateAndEnterBy; }
            set
            {
                this.DateAndEnterBy = $"{QuoteDate} | Entered By: {EnteredBy}";
            }
        }


     

        
        public ChuckModel()
        {
            this.userID = Constants.userID;
            this.EnteredBy = Constants.INITIALS;
            this.DateAndEnterBy = $"{QuoteDate} | Entered By: {EnteredBy}";

        }


    }
}
