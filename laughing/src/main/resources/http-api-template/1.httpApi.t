{"entityClassName":"${apiName}","entityPackageName":"com.yeepay.g3.hotel.core.biz.wxconfig.util.apis"}
/${javaPath}/#packagetopath("${entityPackageName}.${entityClassName}").java
package ${entityPackageName};

import com.yeepay.g3.hotel.core.biz.wxconfig.util.apis.abs.AbstractApi;
import com.yeepay.g3.hotel.core.biz.wxconfig.util.apis.abs.AbstractAsk;
import com.yeepay.g3.hotel.core.biz.wxconfig.util.apis.abs.AbstractReply;
import com.yeepay.g3.hotel.core.util.NetUtils;
import com.yeepay.g3.utils.protocol.JsonUtils2;

public class ${apiName} extends AbstractApi<${apiName}.Ask,${apiName}.Reply>{
    private String address="${address}";
    public ${apiName}(){
        super();
    }
    public ${apiName}(String address){
        super();
        this.address=address;
    }
    public Reply execute(Ask ask){
        return AbstractReply.fromXml(Reply.class, NetUtils.httpPost(address, ask.toXml()));
    }
    public static class Ask extends AbstractAsk{
        @Override
        protected void valid() {
            if(StringUtils.equals("10040007799", this.getCustomerNo())){
                this.setCustomerNo("10012332571");
                this.setEncryptKey("k149C76qr0153G7XD2djsu39ye3G4l81g2q5h8s58bk49xmxKCDx50BX1221");
            }
        }
#foreach($attr in $!askAttrs)
		public String get#upperfirst(${attr})(){
			return attrs.get("${attr}");
		}
		public void set#upperfirst(${attr})(String ${attr}){
			attrs.put("${attr}",${attr});
		}
#end        
    }
    public static class Reply extends AbstractReply{
        public static Reply fromXml(String xml){
            try {
                return JsonUtils2.xml2pojo(xml, Reply.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
#foreach($attr in $!replyAttrs)
		private String $attr;
#end
#foreach($attr in $!replyAttrs)
		public String get#upperfirst($attr)(){
			return this.${attr};
		}
		public void set#upperfirst(${attr})(String ${attr}){
			this.$attr=$attr;
		}
#end
    }
}
