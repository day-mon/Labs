using System;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Collections.Generic;
using System.Text;
using System.Collections.ObjectModel;

using System.Threading.Tasks;
using Newtonsoft.Json;

namespace Chuck
{
    public class WebHandler
    {

        public static readonly WebHandler handler = new WebHandler();
        private readonly HttpClient httpClient = new HttpClient();
        private List<ChuckModel> CachedChuckModelList = new List<ChuckModel>();
        private bool ListCached = false;

        private WebHandler()
        {
            
        }


        public static WebHandler GetInstance()
        {
            return handler;
        }


        async public Task<bool> SendPostAsync(ChuckModel model)
        {
            var url = new Uri(Constants.BASE_URL);
            var json = JsonConvert.SerializeObject(model);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await httpClient.PostAsync(url, content);



            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine($"Wrote {model.ChuckQuote} on {model.QuoteDate} with status code {response.StatusCode}");
                var str = await response.Content.ReadAsStringAsync();
                model = JsonConvert.DeserializeObject<ChuckModel>(str);
                model.DateAndEnterBy = $"{model.QuoteDate} | Entered By: {model.EnteredBy}";
               

                CachedChuckModelList.Add(model);
                return true;
            }
            else
            {
                Console.WriteLine($"Error occured while writing to API Status Code [{response.StatusCode}]");
                return false;
            }

        }

        async public Task<bool> SendPutAsync(ChuckModel model, string EdittedText)
        {
            var ChuckQuote =  EdittedText += " Edit";
            var EnteredBy = $"{Constants.INITIALS} - Edited";
            var userID = Constants.userID;
            var obj = new
            {
                model.id,
                ChuckQuote,
                EnteredBy,
                userID
            };
            var url = $"{Constants.BASE_URL}{Constants.userID}";


          

            Console.WriteLine(url);

            var json = JsonConvert.SerializeObject(model);
            var strContent = new StringContent(json, Encoding.UTF8, "application/json");
            var request = new HttpRequestMessage(HttpMethod.Put, url);
            request.Content = strContent;

            var response = await httpClient.SendAsync(request);

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();


                var element = CachedChuckModelList.Find(item => item.id == model.id);

                element.ChuckQuote = EdittedText;
                element.QuoteDate = DateTimeOffset.Now;
                element.EnteredBy = $"{Constants.INITIALS} - Edited";


                var statusCode = JsonConvert.DeserializeObject<int>(content);
                Console.WriteLine($"Sucessfully deleted ChuckModel with the id of {element.id}");
                return true;
            }
            else
            {
                var content = await response.Content.ReadAsStringAsync();

                Console.WriteLine($"Failed return with error {content}");
                return false;
            }
        }


        async public Task<bool> SendDeleteAsync(ChuckModel model)
        {
            var id = model.id;
            var s = new
            {
                id,
                Constants.userID
            };

            

            var json = JsonConvert.SerializeObject(s);
            var strContent = new StringContent(json, Encoding.UTF8, "application/json");
            var str = await strContent.ReadAsStringAsync();

            var request = new HttpRequestMessage(HttpMethod.Delete, Constants.BASE_URL);
            request.Content = strContent;

            var response = await httpClient.SendAsync(request);



            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var statusCode = JsonConvert.DeserializeObject<int>(content);
                CachedChuckModelList.Remove(model);
                Console.WriteLine($"Sucessfully deleted ChuckModel with the id of {id}");
                return true;
            }
            else
            {
                var content = await response.Content.ReadAsStringAsync();
                

                Console.Write($"Failed return with error {content}");
                return false;
            }
        }

        async public Task<List<ChuckModel>> GetChuckModelsAsync()
        {
            if (ListCached)
            {
                Console.WriteLine( $"Cached {GetCachedList().Count}");
                return CachedChuckModelList;
            }

            HttpResponseMessage response = await httpClient.GetAsync(Constants.GET_URL);

          
            if (response.IsSuccessStatusCode)
            {
                string content = await response.Content.ReadAsStringAsync();

                CachedChuckModelList = JsonConvert.DeserializeObject<List<ChuckModel>>(content);

            }
            ListCached = true;
            return CachedChuckModelList;
        }


        public List<ChuckModel> GetCachedList()
        {
            return CachedChuckModelList;
        }

        async public Task<ObservableCollection<ChuckModel>> GetObservableList()
        {
            if (!ListCached)
            {
               var list =  await GetChuckModelsAsync();

                list.ForEach(item => {
                    item.DateAndEnterBy = $"{item.QuoteDate} | Entered By: {item.EnteredBy}";
                });

             
                return new ObservableCollection<ChuckModel>(list);
            }

            var xd = GetCachedList();

            xd.ForEach(item => {
                item.DateAndEnterBy = $"{item.QuoteDate} | Entered By: {item.EnteredBy}";
            });

            return new ObservableCollection<ChuckModel>(xd);
        }
    }

}
